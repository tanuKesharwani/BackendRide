package com.example.ride.Request;

import com.example.ride.pojo.PageAttributes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserListingRequest {

	private PageAttributes pagingAttrs;
	
	
}
