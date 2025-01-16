package com.example.ride.serviceImpl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.ride.Crud.OtpCrud;
import com.example.ride.Crud.UserRegistrationCrud;
import com.example.ride.Entity.OtpVerificationEntity;
import com.example.ride.Entity.UserRegistration;
import com.example.ride.Request.OTPVerify;
import com.example.ride.Request.UserListingRequest;
import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.Request.userRelatedRequest.BikeDetailsRequest;
import com.example.ride.helper.ExtraFunctions;
import com.example.ride.pojo.BikeDetails;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.MedicalReport;
import com.example.ride.pojo.UserRegistrationDto;
import com.example.ride.security.JwtTokenUtil;
import com.example.ride.service.S3Service;
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
	@Autowired
	private S3Service s3Service;
	@Autowired
	private OtpCrud otpCrud;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PushNotificationService pushNotificationService;
	@Autowired
	private TwilioService twilioService;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserRegistration saveUserDetails(UserRegistrationRequest request) {
		log.info(request);
		try {
			// Validate mandatory fields
			if (request.getPhoneNumber() == null || request.getUserName() == null || request.getFirstName() == null) {
				throw new ResourceNotFoundException("Mandatory fields are missing");
			}

			// Hash the default password
			String hashedPassword = passwordEncoder.encode("zryomile@123");

			// Check if a user with the same phone number already exists
			Optional<UserRegistration> userDetailsOptional = userRegistrationCrud
					.findByPhoneNumber(request.getPhoneNumber());
			if (userDetailsOptional.isPresent()) {
				throw new DuplicateRecordException(
						"User with this phone number " + request.getPhoneNumber() + " is already registered.");
			}

			// Build the UserRegistration object
			UserRegistration.UserRegistrationBuilder builder = UserRegistration.builder()
					.firstName(request.getFirstName())
					.lastName(request.getLastName())
					.userName(request.getUserName())
					.password(hashedPassword)
					.phoneNumber(request.getPhoneNumber());

			// Check if profile picture is provided and set it
			String imageUrl = "";
			if (request.getProfileImage() != null && !request.getProfileImage().isEmpty()) {
				try {
					imageUrl = s3Service.uploadFile(request.getProfileImage(), request.getUserName());
				} catch (Exception e) {
					log.error("Failed to upload image to S3", e);
					throw new GenericException("Failed to upload image: " + e.getMessage());
				}
			}
			builder.profileImage(imageUrl);

			UserRegistration userDetails = builder.build();

			// Save user details in the database
			userRegistrationCrud.save(userDetails);

			// Map saved details to DTO
			UserRegistrationDto responseDto = mapToUserDto(userDetails);

			return userDetails;
			// return new GenericWebServiceResponse(true, "User details saved successfully",
			// responseDto);
		} catch (DuplicateRecordException e) {
			log.error("Duplicate phone number found", e);
			throw new DuplicateRecordException(e.getMessage());
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Mandatory fields verification is required.");
		} catch (Exception e) {
			throw new GenericException("An error occurred while saving user registration details for phone number: "
					+ request.getPhoneNumber() + e);
		}
	}

	@Override
	public GenericWebServiceResponse saveBikeDetails(BikeDetailsRequest request) {
		try {
			// Map incoming request to a list of BikeDetails entities
			List<BikeDetails> newBikeDetailsList = request.getBikeDetails().stream()
					.map(bikeDetailDto -> BikeDetails.builder()
							.bikeNumber(bikeDetailDto.getBikeNumber())
							.bikeModel(bikeDetailDto.getBikeModel())
							.bikeCc(bikeDetailDto.getBikeCc())
							.bikeBrand(bikeDetailDto.getBikeBrand())
							.serviceDate(bikeDetailDto.getServiceDate())
							.build())
					.collect(Collectors.toList());

			// Fetch user details from the database
			UserRegistration userDetails = userRegistrationCrud.findById(ExtraFunctions.getUserName())
					.orElseThrow(() -> new RuntimeException("User with this Id does not exist"));

			// Handle both cases: append to existing bike details or create a new list
			List<BikeDetails> existingBikeDetails = userDetails.getBikeDetails();
			if (existingBikeDetails == null) {
				// If no bike details exist, initialize a new list
				existingBikeDetails = new ArrayList<>();
			}

			// Filter out duplicate bike numbers
			Set<String> existingBikeNumbers = existingBikeDetails.stream()
					.map(BikeDetails::getBikeNumber)
					.collect(Collectors.toSet());

			// Add only non-duplicate bike details to the list
			List<BikeDetails> filteredNewBikeDetails = newBikeDetailsList.stream()
					.filter(newBike -> !existingBikeNumbers.contains(newBike.getBikeNumber()))
					.collect(Collectors.toList());

			if (filteredNewBikeDetails.isEmpty()) {
				return GenericWebServiceResponse.ok();
			}

			// Add the filtered new bike details to the existing list
			existingBikeDetails.addAll(filteredNewBikeDetails);

			// Update the user with the combined list of bike details
			userDetails.setBikeDetails(existingBikeDetails);

			// Save the updated user entity
			userRegistrationCrud.save(userDetails);

			return GenericWebServiceResponse.ok();
		} catch (Exception e) {
			throw new GenericException("An error occurred while adding the bike details");
		}
	}

	@Override
	public GenericWebServiceResponse deleteBikeDetails(String bikeNumber) {
		try {
			// Fetch the user by their ID
			UserRegistration userDetails = userRegistrationCrud.findById(ExtraFunctions.getUserName())
					.orElseThrow(() -> new RuntimeException("User with this ID does not exist"));

			// Check if the user has bike details
			if (userDetails.getBikeDetails() == null || userDetails.getBikeDetails().isEmpty()) {
				return new GenericWebServiceResponse(false,"No bike found ");
			}

			// Remove the bike detail with the matching bike number
			boolean isRemoved = userDetails.getBikeDetails().removeIf(bike -> bike.getBikeNumber().equals(bikeNumber));

			if (!isRemoved) {
				return new GenericWebServiceResponse(false,"No bike found for bike number ",bikeNumber);
			}

			// Save the updated user document
			userRegistrationCrud.save(userDetails);

			return GenericWebServiceResponse.ok();
		}catch(RuntimeException e)
		{
			throw new RuntimeException(e.getMessage());
		} 
		catch (Exception e) {
			log.info(e);
			throw new GenericException("An error occurred while deleting the bike details");
		}
	}

	// @Override
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
	public GenericWebServiceResponse getSingleUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		log.info(userId);
		try {

			UserRegistration userDetails = userRegistrationCrud.findById(userId).orElseThrow(
					() -> new NoRecordFoundException("User not found for the given ID: " + userId));
			UserRegistrationDto resp = mapToUserDto(userDetails);
			return new GenericWebServiceResponse(true, "Login successful for user: ", resp);
		} catch (NoRecordFoundException e) {
			log.error("No record found for userId: {}", userId, e);
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

	@Override
	public GenericWebServiceResponse sendNotification(String fcmToken, String message) {
		try {
			pushNotificationService.send(fcmToken, message);
			// twilioService.sendOtp(fcmToken);
			return new GenericWebServiceResponse(true, "nofication sent to fcm", fcmToken);
		} catch (Exception e) {
			throw new GenericException("An error occurred while login the user ");
		}
	}

	@Override
	public GenericWebServiceResponse sendOTP(String phoneNumber) {
		log.info(phoneNumber);
		try {
			Optional<OtpVerificationEntity> otpOptional = otpCrud.findByPhoneNumber(phoneNumber);

			if (otpOptional.isPresent()) {
				OtpVerificationEntity existingOtp = otpOptional.get();

				return new GenericWebServiceResponse(true, "Existing OTP is still valid", existingOtp);

			}

			// Generate and send a new OTP if no valid OTP exists
			// String newOtp = twilioService.sendOtp(phoneNumber);
			String newOtp = "123456";
			// Save the new OTP to the database
			OtpVerificationEntity newOtpEntity = OtpVerificationEntity.builder()
					.OTP(newOtp)
					.phoneNumber(phoneNumber)
					.createdAt(new Date())
					.build();
			otpCrud.save(newOtpEntity);

			return new GenericWebServiceResponse(true, "New OTP sent successfully", phoneNumber);
		} catch (NotFoundException e) {
			log.info(e);
			throw new NoRecordFoundException("User not found for this given Phone" + e);
		} catch (Exception e) {
			log.info(e);
			throw new GenericException("An error occurred while sending the OTP ");
		}
	}

	@Override
	public GenericWebServiceResponse verifyOTP(OTPVerify request) {
		log.info(request);
		try {
			// Fetch OTP from the database based on the phone number
			Optional<OtpVerificationEntity> otpOptional = otpCrud.findByPhoneNumber(request.getPhoneNumber());

			if (otpOptional.isPresent()) {
				OtpVerificationEntity existingOtp = otpOptional.get();
				log.info("Stored OTP: " + existingOtp.getOTP());
				log.info("Provided OTP: " + request.getOtp());

				// Validate the provided OTP
				if (request.getOtp().equals(existingOtp.getOTP())) {
					// Check if the phone number exists in the user database
					Optional<UserRegistration> userOptional = userRegistrationCrud
							.findByPhoneNumber(request.getPhoneNumber());

					if (userOptional.isPresent()) {
						// User exists, perform login
						UserRegistration existingUser = userOptional.get();
						String token = jwtTokenUtil.generateToken(existingUser.getUserId()); // Generate JWT token

						return new GenericWebServiceResponse(true, "Login successful", token);
					} else {
						// User doesn't exist, perform registration
						String defaultPassword = passwordEncoder.encode("zryomile@123");
						UserRegistrationRequest newUser = UserRegistrationRequest.builder()
								.phoneNumber(request.getPhoneNumber())
								.firstName(request.getFirstName())
								.userName(request.getUserName())
								.profileImage(request.getProfileImage())
								// Default name if not provided
								.build();

						UserRegistration us = saveUserDetails(newUser);
						// Generate JWT token for the new user
						String token = jwtTokenUtil.generateToken(us.getUserId());

						return new GenericWebServiceResponse(true, "Registration successful", token);
					}
				}
			}
			return new GenericWebServiceResponse(false, "OTP verification failed");
		} catch (Exception e) {
			log.error("Error while verifying OTP", e);
			throw new GenericException("An error occurred while verifying the OTP");
		}
	}

}