package com.example.ride.controller;

import static com.example.ride.Request.UserRegistrationRequest.LOGIN_USER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.ride.service.UserRegistrationService;

@RestController
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	}

	@PostMapping(LOGIN_USER)
	public GenericWebServiceResponse loginUserDetails(@RequestBody UserRegistrationRequest request) {
		return userRegistrationService.userlogin(request);
}
