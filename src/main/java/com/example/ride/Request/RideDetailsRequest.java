package com.example.ride.Request;

import java.util.Date;
import java.util.List;

import com.example.ride.pojo.GpsCoordinates;
import com.example.ride.pojo.SponserDetails;

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
public class RideDetailsRequest {

	public static final String CREATE_RIDE_DETAILS = "/create/save-ride-details";

	private String rideName;

	private String rideDetails;

	private Date rideStartTime;

	private Date rideEndTime;

	private GpsCoordinates rideStartLocation;

	private GpsCoordinates rideEndLocation;

	private String createdByUserId;

	private String rideFare;

	private String rideType;

	private String maxRiderAlllowed;

	private Boolean isRidePremiumHolder; // initial true

	private List<GpsCoordinates> stayPoints;

	private List<String> userId;

	private SponserDetails sponsersDetails;

}
