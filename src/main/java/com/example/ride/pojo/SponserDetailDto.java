package com.example.ride.pojo;

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
public class SponserDetailDto {

	private String sponserId;

	private String sponserDetails;
	
	private String sponserName;
	
}
