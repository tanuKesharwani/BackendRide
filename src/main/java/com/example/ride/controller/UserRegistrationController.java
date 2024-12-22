package com.example.ride.controller;

import static com.example.ride.Request.UserRegistrationRequest.SAVE_USER_DETAILS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.service.UserRegistrationService;

@RestController
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@PostMapping(SAVE_USER_DETAILS)
	public GenericWebServiceResponse saveUserDetails(@RequestBody UserRegistrationRequest request) {
		return userRegistrationService.saveUserDetails(request);
	}

}
