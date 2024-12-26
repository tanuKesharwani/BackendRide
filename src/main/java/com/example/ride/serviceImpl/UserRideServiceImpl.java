package com.example.ride.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ride.Crud.RidesDetailsCrud;
import com.example.ride.Crud.UserRegistrationCrud;
import com.example.ride.Entity.RidesDetails;
import com.example.ride.Entity.UserRegistration;
import com.example.ride.Request.RideDetailsRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.RideDetailsDto;
import com.example.ride.pojo.SponserDetails;
import com.example.ride.service.UserRideService;
import com.example.ride.util.GenericException;
import com.example.ride.util.NoRecordFoundException;
import com.example.ride.util.ResourceNotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserRideServiceImpl implements UserRideService {

	@Autowired
	private RidesDetailsCrud rideDetailsCrud;

	@Autowired
	private UserRegistrationCrud userRegistrationCrud;

	@Override
	public GenericWebServiceResponse saveRideDetails(RideDetailsRequest request) {
		log.info(request);
		try {

			UserRegistration userDetails = userRegistrationCrud.findById(request.getCreatedByUserId()).orElseThrow(
					() -> new NoRecordFoundException("User not found for the given ID: " + request.getUserId()));

			if (!request.getIsRidePremiumHolder() && !userDetails.getIsVarifiedLicense()) {
				throw new ResourceNotFoundException("mandatory fields varification and premium need for ride create");
			}

			SponserDetails sponserDetails = SponserDetails.builder()
					.sponserDetails(request.getSponsersDetails().getSponserDetails())
					.sponserName(request.getSponsersDetails().getSponserName()).build();

			RidesDetails rideDetails = RidesDetails.builder().createdByUserId(request.getCreatedByUserId())
					.createdDate(new Date()).rideStartTime(request.getRideStartTime())
					.rideEndTime(request.getRideEndTime()).rideStartLocation(request.getRideStartLocation())
					.rideEndLocation(request.getRideEndLocation()).rideName(request.getRideName())
					.rideDetails(request.getRideDetails()).rideFare(request.getRideFare())
					.maxRiderAlllowed(request.getMaxRiderAlllowed()).isRidePremiumHolder(true)
					.rideType(request.getRideType()).sponserDetails(sponserDetails).userId(request.getUserId())
					.stayPoints(request.getStayPoints()).build();

			rideDetailsCrud.save(rideDetails);

			RideDetailsDto resp = mapToRideDetails(rideDetails);

			return new GenericWebServiceResponse(true, "ride created by user: ", resp);
		} catch (NoRecordFoundException e) {
			throw new NoRecordFoundException("User not found for the given ID");
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("mandatory fields varification and premium need for ride create");
		} catch (Exception e) {
			log.info(e);
			throw new GenericException("An error occurred while creating the ride");
		}

	}

	@Override
	public GenericWebServiceResponse updateRideDetails(RideDetailsDto request) {
		try {
			RidesDetails rideDetails = rideDetailsCrud.findById(request.getRideId()).orElseThrow(
					() -> new NoRecordFoundException("ride not found for the given ID: " + request.getRideId()));
			rideDetails.setRideName(request.getRideName());
			rideDetailsCrud.save(rideDetails);
			RideDetailsDto resp = mapToRideDetails(rideDetails);
			return new GenericWebServiceResponse(true, "ride details update successfully ", resp);
		} catch (NoRecordFoundException e) {
			throw new NoRecordFoundException("User not found for the given ID");
		} catch (Exception e) {
			log.info(e);
			throw new GenericException("An error occurred update the ride details");
		}
	}

	private RideDetailsDto mapToRideDetails(RidesDetails request) {
		RideDetailsDto resp = RideDetailsDto.builder().rideId(request.getRideId()).updatedDate(request.getUpdatedDate())
				.updatedByUserId(request.getUpdatedByUserId()).createdByUserId(request.getCreatedByUserId())
				.createdDate(new Date()).rideStartTime(request.getRideStartTime()).rideEndTime(request.getRideEndTime())
				.rideStartLocation(request.getRideStartLocation()).rideEndLocation(request.getRideEndLocation())
				.rideName(request.getRideName()).rideType(request.getRideType())
				.maxRiderAlllowed(request.getMaxRiderAlllowed()).isRidePremiumHolder(request.getIsRidePremiumHolder())
				.userId(request.getUserId()).rideDetails(request.getRideDetails()).rideFare(request.getRideFare())
				.build();
		return resp;
	}

}
