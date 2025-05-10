package com.example.ride.Request;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityFeedRequest {

    public static final String CREATE_New_Feed_Post = "/feed/createNew";
    public static final String GET_Feed ="/feed";

    private String userId;

    private String feedDescription;

    private MultipartFile feedImage;

    // private String likeCount;

    // private List<String>likedByUserID; 

    // private String rideJoinId;
}
