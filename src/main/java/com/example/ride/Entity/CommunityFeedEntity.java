package com.example.ride.Entity;


import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "communityFeed")
public class CommunityFeedEntity implements Serializable {
    private static final long serialVersionUID = 1L;


	@Id
	private String feedID;
    
    @Field(name="creator_id")
	private String userId;

    @Field(name="feed_description")
    private String feedDescription;

    @Field(name="feed_image")
    private String feedImage;

    @Field(name="like_count")
    private int likeCount;

    @Field(name="like_by_whome")
    private List<String>likedByUserID; 

    @Field(name = "ride_join_id")
    private String rideJoinId;
}


