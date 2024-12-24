package com.example.ride.service;

import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.UserRegistrationDto;

public interface UserRegistrationService {

	public GenericWebServiceResponse saveUserDetails(UserRegistrationRequest request);

	public GenericWebServiceResponse userlogin(UserRegistrationRequest request);

	public GenericWebServiceResponse getAllUserDetails();

	public GenericWebServiceResponse getSingleUserDetails(UserRegistrationDto request);

	public GenericWebServiceResponse updateSingleUserDetails(UserRegistrationDto request);

}
