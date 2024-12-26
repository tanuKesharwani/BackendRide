package com.example.ride.service;

import com.example.ride.Request.RideDetailsRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.RideDetailsDto;

public interface UserRideService {

	public GenericWebServiceResponse saveRideDetails(RideDetailsRequest request);
	
	public GenericWebServiceResponse updateRideDetails(RideDetailsDto request);
	
}
