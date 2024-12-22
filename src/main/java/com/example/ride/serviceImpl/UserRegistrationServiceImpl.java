package com.example.ride.serviceImpl;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ride.Crud.UserRegistrationCrud;
import com.example.ride.Entity.UserRegistration;
import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.pojo.BikeDetails;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.MedicalReport;
import com.example.ride.pojo.UserRegistrationDto;
import com.example.ride.service.UserRegistrationService;
import com.example.ride.util.DuplicateRecordException;
import com.example.ride.util.GenericException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private UserRegistrationCrud userRegistrationCrud;

	@Override
	public GenericWebServiceResponse saveUserDetails(UserRegistrationRequest request) {
		log.info(request);
		try {
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
					.emailAddress(request.getEmailAddress()).phoneNumber(request.getPhoneNumber())
					.bloodGroup(request.getBloodGroup()).address(request.getAddress()).gender(request.getGender())
					.licenseNumber(request.getLicenseNumber()).isVarifiedLicense(request.getIsVarifiedLicense())
					.bikeDetails(bikeDetailsList).medicalReport(medicalReportsList).createdDate(new Date())
					.licenseFrontImg(convertBase64ToBinary(request.getLicenseFrontImg()))
					.licenseBackImg(convertBase64ToBinary(request.getLicenseBackImg()))
					.build();
			userRegistrationCrud.save(Details);

			resp = UserRegistrationDto.builder().firstName(Details.getFirstName()).lastName(Details.getLastName())
					.userId(Details.getUserId()).emailAddress(Details.getEmailAddress()).userName(Details.getUserName())
					.createdDate(Details.getCreatedDate()).phoneNumber(Details.getPhoneNumber())
					.licenseNumber(Details.getLicenseNumber()).Address(Details.getAddress()).gender(Details.getGender())
					.build();

			return new GenericWebServiceResponse(true, "user Details saved successfully", resp);
		} catch (DuplicateRecordException e) {
			log.error("Duplicate email found", e);
			throw new DuplicateRecordException(e.getMessage());
		} catch (Exception e) {
			throw new GenericException("An error occurred while saving user registration details for email address: "
					+ request.getEmailAddress());
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

}
