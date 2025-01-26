package com.example.ride.controller;

import static com.example.ride.Request.UserRegistrationRequest.LOGIN_USER;
import static com.example.ride.Request.UserRegistrationRequest.SAVE_USER_DETAILS;
import static com.example.ride.pojo.UserRegistrationDto.GET_ALL_USER_DETAILS;
import static com.example.ride.pojo.UserRegistrationDto.UPDATE_USER_PROFILE;
import static com.example.ride.pojo.UserRegistrationDto.GET_SINGLE_USER_DETAILS;
import static com.example.ride.Request.userRelatedRequest.BikeDetailsRequest.ADD_VEHICLE_STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ride.Entity.UserRegistration;
import com.example.ride.Request.CommunityFeedRequest;
import com.example.ride.Request.NotificationRequest;
import com.example.ride.Request.OTPVerify;
import com.example.ride.Request.UserListingRequest;
import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.Request.userRelatedRequest.BikeDetailsRequest;
import com.example.ride.helper.EndPointConstants;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.UserRegistrationDto;
import com.example.ride.service.UserRegistrationService;

import jakarta.websocket.server.PathParam;

@RestController
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping(GET_ALL_USER_DETAILS)
	public GenericWebServiceResponse getUserDetails(@RequestBody UserListingRequest request) {
		return userRegistrationService.getAllUserDetails(request);
	}

	@PostMapping(GET_SINGLE_USER_DETAILS)
	public GenericWebServiceResponse getSingleUserDetails() {
		return userRegistrationService.getSingleUserDetails();
	}

	@PostMapping(value = SAVE_USER_DETAILS, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public UserRegistration saveUserDetails(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("userName") String userName,
			@RequestParam("phoneNumber") String phoneNumber,

			@RequestParam(value = "profileImage", required = false) MultipartFile feedImage) {

		UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
		userRegistrationRequest.setUserName(userName);
		userRegistrationRequest.setFirstName(firstName);
		userRegistrationRequest.setLastName(lastName);
		userRegistrationRequest.setPhoneNumber(phoneNumber);
		userRegistrationRequest.setProfileImage(feedImage);

		return userRegistrationService.saveUserDetails(userRegistrationRequest);
	}

	@PostMapping(ADD_VEHICLE_STRING)
	public GenericWebServiceResponse saveBikeDetails(@RequestBody BikeDetailsRequest request) {
		return userRegistrationService.saveBikeDetails(request);
	}
	@PostMapping(EndPointConstants.addMedicalDetails)
	public GenericWebServiceResponse saveMedicalDetails(@RequestBody BikeDetailsRequest request) {
		return userRegistrationService.saveBikeDetails(request);
	}

	@DeleteMapping("/auth/user/deleteVehicle")
	public GenericWebServiceResponse deleteVehicleDetails(@RequestParam("bikeNumber") String bikeNumber) {
		return userRegistrationService.deleteBikeDetails(bikeNumber);
	}

	@PostMapping(UPDATE_USER_PROFILE)
	public GenericWebServiceResponse updateUserDetails(@RequestBody UserRegistrationDto request) {
		return userRegistrationService.updateSingleUserDetails(request);
	}

	@PostMapping("/sendNotification")
	public GenericWebServiceResponse sendNotification(@RequestBody NotificationRequest request) {
		return userRegistrationService.sendNotification(request.getFcmToken(), "hello faguu jii");
	}

	@PostMapping("/send_otp")
	public GenericWebServiceResponse sendOTP(@RequestBody OTPVerify request) {
		return userRegistrationService.sendOTP(request.getPhoneNumber());
	}

	@PostMapping(value = "/verify_otp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public GenericWebServiceResponse verifyOTP(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("userName") String userName,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("otp") String otp,
			@RequestParam(value = "profileImage", required = false) MultipartFile feedImage) {

		OTPVerify otpVerify = new OTPVerify();

		otpVerify.setUserName(userName != null ? userName : "");
		otpVerify.setFirstName(firstName != null ? firstName : "");
		otpVerify.setLastName(lastName != null ? lastName : "");
		otpVerify.setPhoneNumber(phoneNumber);
		if (feedImage != null) {

			otpVerify.setProfileImage(feedImage);
		}
		otpVerify.setOtp(otp);

		return userRegistrationService.verifyOTP(otpVerify);
	}
}