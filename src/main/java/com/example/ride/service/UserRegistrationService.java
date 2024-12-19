package com.example.ride.service;

import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.pojo.GenericWebServiceResponse;

public interface UserRegistrationService {

	public GenericWebServiceResponse saveUserDetails(UserRegistrationRequest request);
}
