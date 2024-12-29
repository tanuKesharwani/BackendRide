package com.example.ride.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ride.Crud.CommunityFeedCrud;
import com.example.ride.Entity.CommunityFeedEntity;
import com.example.ride.Request.CommunityFeedRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.service.CommunityFeedService;
import com.example.ride.service.S3Service;
import com.example.ride.util.GenericException;
import com.example.ride.util.LimitExhaustedException;
import com.example.ride.util.ResourceNotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CommunityFeedImpl implements CommunityFeedService {

    @Autowired
    private CommunityFeedCrud communityFeedCrud;

    @Autowired
    private S3Service s3Service;
    @Override
    public GenericWebServiceResponse createCommunityFeed(CommunityFeedRequest request) {
        log.info("Creating community feed for user: {}", request.getUserId());
        
        try {
            // Validate request
            if (request.getUserId() == null || request.getUserId().trim().isEmpty()) {
                throw new ResourceNotFoundException("User ID is required");
            }

            if (request.getFeedDescription() == null || request.getFeedDescription().trim().isEmpty()) {
                throw new ResourceNotFoundException("Feed description is required");
            }

            // Check if user has reached the post limit
            List<CommunityFeedEntity> userFeeds = communityFeedCrud.findAllByUserId(request.getUserId());
            if (userFeeds != null && userFeeds.size() >= 100) {
                throw new LimitExhaustedException("User has reached the maximum limit of 100 posts", 100);
            }

            String imageUrl = null;
            if (request.getFeedImage() != null && !request.getFeedImage().isEmpty()) {
                try {
                    imageUrl = s3Service.uploadFile(request.getFeedImage(), request.getUserId());
                } catch (Exception e) {
                    log.error("Failed to upload image to S3", e);
                    throw new GenericException("Failed to upload image: " + e.getMessage());
                }
            }
            
            // Create new feed
            CommunityFeedEntity communityFeed = CommunityFeedEntity.builder()
                .userId(request.getUserId())
                .feedDescription(request.getFeedDescription())
                .likeCount(0)
                .feedImage(imageUrl)
                .likedByUserID(new ArrayList<>())  // Initialize empty list
                .build();

            // Save feed
            communityFeed = communityFeedCrud.save(communityFeed);
            
            log.info("Created community feed with ID: {}", communityFeed.getFeedID());
            
            return new GenericWebServiceResponse(true,"feed added scucessfull",200);
        } catch (ResourceNotFoundException | LimitExhaustedException e) {
            log.error("Error in createCommunityFeed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error in createCommunityFeed", e);
            throw new GenericException("Failed to create community feed: " + e.getMessage());
        }
    }
}