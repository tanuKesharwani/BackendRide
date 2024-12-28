package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.ride.pojo.GpsCoordinates;
import com.example.ride.pojo.SponserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rides_details")
public class RidesDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String rideId;

	@Field(name = "ride_details")
	private String rideDetails;

	@Field(name = "ride_name")
	private String rideName;

	@Field(name = "created_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date createdDate;

	@Field(name = "updated_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date updatedDate;

	@Field(name = "ride_start_time")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date rideStartTime;

	@Field(name = "ride_ends_time")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date rideEndTime;

	@Field(name = "ride_start_location")
	private GpsCoordinates rideStartLocation;

	@Field(name = "ride_end_location")
	private GpsCoordinates rideEndLocation;

	@Field(name = "created_by_user_id")
	private String createdByUserId;

	@Field(name = "updated_by_user_id")
	private String updatedByUserId;

	@Field(name = "ride_fare")
	private String rideFare;

	@Field(name = "ride_type")
	private String rideType;

	@Field(name = "max_rider_allowed")
	private String maxRiderAlllowed;

	@Field(name = "is_ride_premium_holder")
	private Boolean isRidePremiumHolder; // initial true

	@Field(name = "joined_user")
	private List<String> userId;
	
	@Field(name="stay_points")
	private List<GpsCoordinates> stayPoints;
	
	@Field(name="sponser_details")
	private SponserDetails sponserDetails;

}
