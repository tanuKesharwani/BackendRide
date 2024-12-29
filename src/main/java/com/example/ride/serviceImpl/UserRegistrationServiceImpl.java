package com.example.ride.serviceImpl;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ride.Crud.UserRegistrationCrud;
import com.example.ride.Entity.UserRegistration;
import com.example.ride.Request.UserListingRequest;
import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.pojo.BikeDetails;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.MedicalReport;
import com.example.ride.pojo.UserRegistrationDto;
import com.example.ride.service.UserRegistrationService;
import com.example.ride.util.DuplicateRecordException;
import com.example.ride.util.GenericException;
import com.example.ride.util.NoRecordFoundException;
import com.example.ride.util.ResourceNotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserRegistrationCrud userRegistrationCrud;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public GenericWebServiceResponse saveUserDetails(UserRegistrationRequest request) {
		log.info(request);
		try {
			if (request.getPhoneNumber() == null || request.getUserName() == null || request.getFirstName() == null
					|| request.getPassword() == null) {
				throw new ResourceNotFoundException("mandatory fields Missing");
			}
			String hashedPassword = passwordEncoder.encode(request.getPassword());

			UserRegistrationDto resp = null;
			Optional<UserRegistration> userDetailsOptional = userRegistrationCrud
					.findByPhoneNumber(request.getPhoneNumber());
			if (userDetailsOptional.isPresent()) {
				throw new DuplicateRecordException(
						"User with this phone number " + request.getPhoneNumber() + " is already registered.");
			}
			List<BikeDetails> bikeDetailsList = request.getBikeDetails().stream()
					.map(bikeDetailDto -> BikeDetails.builder().bikeNumber(bikeDetailDto.getBikeNumber())
							.bikeModel(bikeDetailDto.getBikeModel()).bikeCc(bikeDetailDto.getBikeCc())
							.serviceDate(bikeDetailDto.getServiceDate()).build())
					.collect(Collectors.toList());

			List<MedicalReport> medicalReportsList = request.getMedicalReport().stream()
					.map(medicalReportDto -> MedicalReport.builder()
							.medicalCondition(medicalReportDto.getMedicalCondition()).build())
					.collect(Collectors.toList());

			UserRegistration Details = UserRegistration.builder().firstName(request.getFirstName())
					.lastName(request.getLastName()).userName(request.getUserName())
					.licenseBackImg(convertBase64ToBinary(request.getLicenseBackImg())).password(hashedPassword)

					.emailAddress(request.getEmailAddress()).phoneNumber(request.getPhoneNumber())
					.bloodGroup(request.getBloodGroup()).address(request.getAddress()).gender(request.getGender())
					.licenseNumber(request.getLicenseNumber()).isVarifiedLicense(request.getIsVarifiedLicense())
					.bikeDetails(bikeDetailsList).medicalReport(medicalReportsList).createdDate(new Date())
					.licenseFrontImg(convertBase64ToBinary(request.getLicenseFrontImg()))
					.licenseBackImg(convertBase64ToBinary(request.getLicenseBackImg())).build();
			userRegistrationCrud.save(Details);
			resp = mapToUserDto(Details);
			return new GenericWebServiceResponse(true, "user Details saved successfully", resp);
		} catch (DuplicateRecordException e) {
			log.error("Duplicate email found", e);
			throw new DuplicateRecordException(e.getMessage());
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("mandatory fields varification and premium need for ride create");
		} catch (Exception e) {
			throw new GenericException("An error occurred while saving user registration details for email address: "
					+ request.getEmailAddress() + e);
		}

	}

	private Binary convertBase64ToBinary(String base64Image) {
		try {
			String actualBase64Data = base64Image.contains(",") ? base64Image.split(",")[1] : base64Image;
			byte[] decodedBytes = Base64.getDecoder().decode(actualBase64Data);
			return new Binary(decodedBytes);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid Base64 data", e);
		}

	}

	@Override
	public GenericWebServiceResponse userlogin(UserRegistrationRequest request) {
		try {
			UserRegistration user = userRegistrationCrud.findByPhoneNumber(request.getPhoneNumber())
					.orElseThrow(() -> new RuntimeException("User with this phone number does not exist"));
			if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
				throw new RuntimeException("Invalid credentials: Incorrect password");
			}
			return new GenericWebServiceResponse(true, "Login successful for user: " + user.getFirstName());
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new GenericException("An error occurred while login the user " + request.getPhoneNumber());
		}
	}

	@Override
	public GenericWebServiceResponse getAllUserDetails(UserListingRequest request) {
		try {
			PageRequest pageable = PageRequest.of(request.getPagingAttrs().getPageNum(),
					request.getPagingAttrs().getPageSize());
			Page<UserRegistration> userDetails = userRegistrationCrud.findAll(pageable);

			List<UserRegistrationDto> userDtos = userDetails.stream().map(this::mapToUserDto)
					.collect(Collectors.toList());
			return new GenericWebServiceResponse(true, "user Details fetched successfully: ", userDtos);
		} catch (Exception e) {
			throw new GenericException("An error occurred while login the user ");
		}
	}

	@Override
	public GenericWebServiceResponse getSingleUserDetails(UserRegistrationDto request) {
		log.info(request.getUserId());
		try {
			UserRegistration userDetails = userRegistrationCrud.findById(request.getUserId()).orElseThrow(
					() -> new NoRecordFoundException("User not found for the given ID: " + request.getUserId()));
			UserRegistrationDto resp = mapToUserDto(userDetails);
			return new GenericWebServiceResponse(true, "Login successful for user: ", resp);
		} catch (NoRecordFoundException e) {
			log.error("No record found for userId: {}", request.getUserId(), e);
			throw e;
		} catch (Exception e) {
			throw new GenericException("An error occurred while getting the user ");
		}
	}

	@Override
	public GenericWebServiceResponse updateSingleUserDetails(UserRegistrationDto request) {
		log.info(request);
		try {

			List<BikeDetails> bikeDetailsList = request.getBikeDetails().stream()
					.map(bikeDetailDto -> BikeDetails.builder().bikeNumber(bikeDetailDto.getBikeNumber())
							.bikeModel(bikeDetailDto.getBikeModel()).bikeCc(bikeDetailDto.getBikeCc())
							.serviceDate(bikeDetailDto.getServiceDate()).build())
					.collect(Collectors.toList());

			List<MedicalReport> medicalReportsList = request.getMedicalReport().stream()
					.map(medicalReportDto -> MedicalReport.builder()
							.medicalCondition(medicalReportDto.getMedicalCondition()).build())
					.collect(Collectors.toList());
			UserRegistration userDetails = userRegistrationCrud.findById(request.getUserId())
					.orElseThrow(() -> new RuntimeException("User with this Id does not exist"));

			userDetails.setAddress(request.getAddress());
			userDetails.setUserName(request.getUserName());
			userDetails.setBikeDetails(bikeDetailsList);
			userDetails.setMedicalReport(medicalReportsList);

			userRegistrationCrud.save(userDetails);
			System.out.println(userDetails.getUserName());
			UserRegistrationDto resp = mapToUserDto(userDetails);
			return new GenericWebServiceResponse(true, "update single userProfile of user: ", resp);

		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new GenericException("An error occurred while update the user ");
		}

	}

	private UserRegistrationDto mapToUserDto(UserRegistration Details) {
		UserRegistrationDto resp = UserRegistrationDto.builder().firstName(Details.getFirstName())
				.lastName(Details.getLastName()).userId(Details.getUserId()).emailAddress(Details.getEmailAddress())
				.userName(Details.getUserName()).createdDate(Details.getCreatedDate())
				.phoneNumber(Details.getPhoneNumber()).licenseNumber(Details.getLicenseNumber())
				.Address(Details.getAddress()).gender(Details.getGender()).build();
		return resp;
	}

}