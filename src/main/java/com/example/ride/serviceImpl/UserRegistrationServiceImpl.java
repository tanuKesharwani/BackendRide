package com.example.ride.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.service.UserRegistrationService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Override
	public GenericWebServiceResponse saveUserDetails(UserRegistrationRequest request) {
		return null;
	}

}
