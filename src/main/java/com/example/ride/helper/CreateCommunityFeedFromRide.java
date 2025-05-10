package com.example.ride.helper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ride.Entity.RidesDetails;
import com.example.ride.Request.CommunityFeedRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.service.CommunityFeedService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data

public class CreateCommunityFeedFromRide {
    @Autowired
    public CommunityFeedService communityFeedService;

     public   void createCommunityFeedForRide(RidesDetails ride) {
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
}
