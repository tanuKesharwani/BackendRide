package com.example.ride.controller;

import static com.example.ride.Request.UserRegistrationRequest.SAVE_USER_DETAILS;

import static com.example.ride.Request.UserRegistrationRequest.LOGIN_USER;
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
    @PostMapping("/create")
    public GenericWebServiceResponse createData(UserRegistrationRequest request){
        return userRegistrationService.saveUserDetails(request);
	}
	@PostMapping(SAVE_USER_DETAILS)
	public GenericWebServiceResponse saveUserDetails(@RequestBody UserRegistrationRequest request) {
		return userRegistrationService.saveUserDetails(request);
	}

	}

	@PostMapping(LOGIN_USER)
	public GenericWebServiceResponse loginUserDetails(@RequestBody UserRegistrationRequest request) {
		return userRegistrationService.userlogin(request);
}
