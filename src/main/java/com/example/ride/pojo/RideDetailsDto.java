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
public class RideDetailsDto {

	private Long rideId;

	private String rideDetails;

	private Date createdDate;

	private Date updatedDate;

	private Date rideStartTime;

	private Date rideEndTime;

	private GpsCoordinates rideStartLocation;

	private GpsCoordinates rideEndLocation;

	private UUID createdByUserId;

	private UUID updatedByUserId;

	private String rideFare;

}
