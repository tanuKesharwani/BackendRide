package com.example.ride.controller;

import static com.example.ride.Request.RideDetailsRequest.CREATE_RIDE_DETAILS;
import static com.example.ride.pojo.RideDetailsDto.UPDATE_RIDE_DETAILS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ride.Request.RideDetailsRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.RideDetailsDto;
import com.example.ride.service.UserRideService;

@RestController
public class UserRideController {

	@Autowired
	private UserRideService userRideService;

	@PostMapping(CREATE_RIDE_DETAILS)
	public GenericWebServiceResponse saveRideDetails(@RequestBody RideDetailsRequest request) {
		return userRideService.saveRideDetails(request);
	}

	@PostMapping(UPDATE_RIDE_DETAILS)
	public GenericWebServiceResponse updateRideDetails(@RequestBody RideDetailsDto request) {
		return userRideService.updateRideDetails(request);
	}

}
