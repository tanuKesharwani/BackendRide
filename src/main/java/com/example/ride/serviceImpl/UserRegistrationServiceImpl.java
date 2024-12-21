package com.example.ride.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ride.Crud.BikeDetailsCrud;
import com.example.ride.Entity.BikeDetails;
import com.example.ride.Request.UserRegistrationRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.service.UserRegistrationService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	@Autowired
	private BikeDetailsCrud bikeDetailsCrud;

	@Override
	public GenericWebServiceResponse saveUserDetails(UserRegistrationRequest request) {

		BikeDetails bikeRide = new BikeDetails();
		bikeRide.setBikeCc("200");
		bikeRide.setBikeModel("bullet");
		bikeDetailsCrud.save(bikeRide);
		return null;
	}

}
