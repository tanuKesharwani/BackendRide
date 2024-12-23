package com.example.ride.controller;

import static com.example.ride.Request.UserRegistrationRequest.LOGIN_USER;
import static com.example.ride.Request.UserRegistrationRequest.SAVE_USER_DETAILS;
import static com.example.ride.pojo.UserRegistrationDto.GET_ALL_USER_DETAILS;
import static com.example.ride.pojo.UserRegistrationDto.UPDATE_USER_PROFILE;
import static com.example.ride.pojo.UserRegistrationDto.GET_SINGLE_USER_DETAILS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.UserRegistrationDto;
import com.example.ride.service.UserRegistrationService;

@RestController
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping(GET_ALL_USER_DETAILS)
	public GenericWebServiceResponse getUserDetails() {
		return userRegistrationService.getAllUserDetails();
	}

	@PostMapping(GET_SINGLE_USER_DETAILS)
	public GenericWebServiceResponse getSingleUserDetails(@RequestBody UserRegistrationDto request) {
		return userRegistrationService.getSingleUserDetails(request);
	}

	@PostMapping(SAVE_USER_DETAILS)
	public GenericWebServiceResponse saveUserDetails(@RequestBody UserRegistrationRequest request) {
		return userRegistrationService.saveUserDetails(request);
	}

	@PostMapping(LOGIN_USER)
	public GenericWebServiceResponse loginUserDetails(@RequestBody UserRegistrationRequest request) {
		return userRegistrationService.userlogin(request);
	}

	@PostMapping(UPDATE_USER_PROFILE)
	public GenericWebServiceResponse updateUserDetails(@RequestBody UserRegistrationDto request) {
		return userRegistrationService.updateSingleUserDetails(request);
	}

}