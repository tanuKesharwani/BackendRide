package com.example.ride.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public GenericWebServiceResponse saveUserDetails(UserRegistrationRequest request) {

			String hashedPassword = passwordEncoder.encode(request.getPassword());

		bikeRide.setBikeModel("bullet");
		bikeDetailsCrud.save(bikeRide);
		return null;
					.licenseBackImg(convertBase64ToBinary(request.getLicenseBackImg())).password(hashedPassword)
			log.error("Duplicate phone number found", e);
		}
	}

	@Override
	public GenericWebServiceResponse userlogin(UserRegistrationRequest request) {
		try {
			UserRegistration user = userRegistrationCrud.findByPhoneNumber(request.getPhoneNumber())
					.orElseThrow(() -> new RuntimeException("User with this phone number does not exist"));
			if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
				throw new RuntimeException("Invalid credentials: Incorrect password");
			}
			return new GenericWebServiceResponse(true, "Login successful for user: " + user.getFirstName());
		} catch (RuntimeException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new GenericException("An error occurred while login the user " + request.getPhoneNumber());
	}

}
