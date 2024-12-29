package com.example.ride.pojo.Dto;


import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityFeedDto  {
	

	@Id
	private String feedID;

	private String userId;

    private String feedDescription;

    private String feedImage;

    private String likeCount;

    private List<String>likedByUserID; 

    private String rideJoinId;
}
