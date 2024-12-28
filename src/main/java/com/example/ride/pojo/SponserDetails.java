package com.example.ride.pojo;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SponserDetails {

	@Id
	private String sponserId;

	private String sponserDetails;
	
	private String sponserName;
	
}
