package com.example.ride.pojo;

import java.util.Date;
import java.util.UUID;

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
public class BikeDetailsDto {

	private Long bikeId;

	private String bikeNumber;

	private String rideName;

	private UUID userRegistrationId;

	private String bikeModel;

	private String bikeCc;

	private Date serviceDate;

}
