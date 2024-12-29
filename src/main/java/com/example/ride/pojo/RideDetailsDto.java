package com.example.ride.pojo;

import java.util.Date;
import java.util.List;

import com.example.ride.pojo.Enums.RideTypes;

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

	public static final String UPDATE_RIDE_DETAILS = "/update/ride-details";

	private String rideId;

	private String rideName;

	private String rideDetails;

	private Date createdDate;

	private Date updatedDate;

	private Date rideStartTime;

	private Date rideEndTime;

	private GpsCoordinates rideStartLocation;

	private GpsCoordinates rideEndLocation;

	private String createdByUserId;

	private String updatedByUserId;

	private String rideFare;

	private RideTypes rideType;

	private String maxRiderAlllowed;

	private Boolean isRidePremiumHolder; // initial true

	private List<String> userId;

}
