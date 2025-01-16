package com.example.ride.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ride.Crud.RidesDetailsCrud;
import com.example.ride.Crud.UserRegistrationCrud;
import com.example.ride.Entity.RidesDetails;
import com.example.ride.Entity.UserRegistration;
import com.example.ride.Request.CommunityFeedRequest;
import com.example.ride.Request.RideDetailsRequest;
import com.example.ride.Request.RideInviteRequest;
import com.example.ride.helper.CreateCommunityFeedFromRide;
import com.example.ride.helper.GenerateFeedBackDescription;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.RideDetailsDto;
import com.example.ride.pojo.SponserDetails;
import com.example.ride.service.CommunityFeedService;
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

	@Autowired
	private PushNotificationService pushNotificationService;
	@Autowired
	private CommunityFeedService communityFeedService;
	@Override
	public GenericWebServiceResponse saveRideDetails(RideDetailsRequest request) {
		log.info(request);
		try {

			UserRegistration userDetails = userRegistrationCrud.findById(request.getCreatedByUserId()).orElseThrow(
					() -> new NoRecordFoundException("User not found for the given ID: " + request.getUserId()));

			// if (!userDetails.getIsPremium() && !userDetails.getIsVarifiedLicense()) {
			// 	throw new ResourceNotFoundException("mandatory fields varification and premium need for ride create");
			// }
			
			validateRideTypeSpecificRules(request);

			SponserDetails sponserDetails = SponserDetails.builder()
					.sponserDetails(request.getSponsersDetails().getSponserDetails())
					.sponserName(request.getSponsersDetails().getSponserName()).build();

			RidesDetails rideDetails = RidesDetails.builder().createdByUserId(request.getCreatedByUserId())
					.createdDate(new Date()).rideStartTime(request.getRideStartTime())
					.rideEndTime(request.getRideEndTime()).rideStartLocation(request.getRideStartLocation())
					.rideEndLocation(request.getRideEndLocation()).rideName(request.getRideName())
					.rideDetails(request.getRideDetails()).rideFare(request.getRideFare())
					.maxRiderAlllowed(request.getMaxRiderAlllowed())
					.rideType(request.getRideType()).sponserDetails(sponserDetails).userId(request.getUserId())
					.stayPoints(request.getStayPoints()).build();

			

			rideDetailsCrud.save(rideDetails);

			createCommunityFeedForRide(rideDetails);
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
				.maxRiderAlllowed(request.getMaxRiderAlllowed())
				.userId(request.getUserId()).rideDetails(request.getRideDetails()).rideFare(request.getRideFare())
				.build();
		return resp;
	}

	private void validateRideTypeSpecificRules(RideDetailsRequest request) {
        switch (request.getRideType()) {
            case SPONSORED:
                if (request.getSponsersDetails() == null || 
                    request.getSponsersDetails().getSponserName() == null ||
                    request.getSponsersDetails().getSponserDetails() == null) {
                    throw new GenericException("Sponsor details are required for SPONSORED rides");
                }
                break;
            case GROUP:
                if (request.getMaxRiderAlllowed() == null || 
                    Integer.parseInt(request.getMaxRiderAlllowed()) <= 1) {
                    throw new GenericException("Group rides must allow more than one rider");
                }
                break;
            case COMPETITIVE:
                if (request.getRideFare() == null || request.getRideFare().isEmpty()) {
                    throw new GenericException("Ride fare is mandatory for COMPETITIVE rides");
                }
                break;
        }
    }


	public  void createCommunityFeedForRide(RidesDetails ride) {
        try {
            // Create feed description based on ride type and details
            String feedDescription = GenerateFeedBackDescription.generateFeedDescription(ride);

            CommunityFeedRequest feedRequest = CommunityFeedRequest.builder()
                    .userId(ride.getCreatedByUserId())
                    .feedDescription(feedDescription)
                    // You can add image later if needed
                    .build();

            // Create community feed post
            GenericWebServiceResponse feedResponse = communityFeedService.createCommunityFeed(feedRequest);
            log.info("Community feed created for ride: {}", ride.getRideId());

        } catch (Exception e) {
            // Log error but don't throw - we don't want to fail ride creation if feed fails
            log.error("Failed to create community feed for ride {}: {}", ride.getRideId(), e.getMessage());
        }
    }
   
	public void sendInviteToRides(RideInviteRequest request)
	{
		 try {
            // Step 1: Fetch users from the database using the provided list of user IDs
            List<UserRegistration> invitedUsers = userRegistrationCrud.findByUserIdIn(request.getInvitedUserId());

            // Step 2: Extract FCM tokens from the users
            List<String> fcmTokens = invitedUsers.stream()
                                                  .map(UserRegistration::getFcmToken)
												  .filter(token -> token != null) // Assuming User has a getFcmToken() method
                                                  .collect(Collectors.toList());

            // Step 3: Send notifications to the users using their FCM tokens
            pushNotificationService.sendToMultipleTokens(fcmTokens, "You are invited to a ride!");

            // Step 4: Save the invited users' IDs to the RideDetails
            RidesDetails rideDetails = rideDetailsCrud.findByRideId(request.getRideId());
            if (rideDetails == null) {
                // Handle the case when ride details are not found (maybe throw an exception)
                throw new RuntimeException("Ride not found");
            }

            rideDetails.setRequestedUser(request.getInvitedUserId());
            rideDetailsCrud.save(rideDetails);

        } catch (Exception e) {
            // Handle exception, log it or rethrow it
            System.err.println("Error sending ride invite: " + e.getMessage());
        }
	}
	
}
