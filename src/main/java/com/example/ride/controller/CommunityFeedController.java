package com.example.ride.controller;

import static com.example.ride.Request.CommunityFeedRequest.CREATE_New_Feed_Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ride.Request.CommunityFeedRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.service.CommunityFeedService;

@RestController
public class CommunityFeedController {
    
    @Autowired
    CommunityFeedService communityFeedService;
    
    @PostMapping(value = CREATE_New_Feed_Post, 
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericWebServiceResponse createNewCommunityPost(
            @RequestParam("userId") String userId,
            @RequestParam("feedDescription") String feedDescription,
            @RequestParam(value = "feedImage", required = false) MultipartFile feedImage) {
        
        CommunityFeedRequest communityFeedRequest = new CommunityFeedRequest();
        communityFeedRequest.setUserId(userId);
        communityFeedRequest.setFeedDescription(feedDescription);
        communityFeedRequest.setFeedImage(feedImage);
        
        return communityFeedService.createCommunityFeed(communityFeedRequest);
    }
}