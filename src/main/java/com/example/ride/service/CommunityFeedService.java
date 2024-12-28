package com.example.ride.service;

import com.example.ride.Request.CommunityFeedRequest;
import com.example.ride.pojo.GenericWebServiceResponse;

public interface CommunityFeedService {

        public GenericWebServiceResponse createCommunityFeed(CommunityFeedRequest feedRequest);

} 
