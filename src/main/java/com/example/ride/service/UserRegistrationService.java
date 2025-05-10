package com.example.ride.service;

import com.example.ride.Entity.UserRegistration;
import com.example.ride.Request.OTPVerify;
import com.example.ride.Request.UserListingRequest;
import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.Request.userRelatedRequest.BikeDetailsRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.UserRegistrationDto;

public interface UserRegistrationService {

	public UserRegistration saveUserDetails(UserRegistrationRequest request);


	public GenericWebServiceResponse getSingleUserDetails();
    public GenericWebServiceResponse saveBikeDetails(BikeDetailsRequest request);
	public GenericWebServiceResponse updateSingleUserDetails(UserRegistrationDto request);
	public GenericWebServiceResponse deleteBikeDetails(String bikeNumber);
	public GenericWebServiceResponse getAllUserDetails(UserListingRequest request);
	
	public GenericWebServiceResponse sendNotification(String fcmToken,String message);
	public GenericWebServiceResponse sendOTP(String phoneNumber);

	public GenericWebServiceResponse verifyOTP(OTPVerify request);


}
