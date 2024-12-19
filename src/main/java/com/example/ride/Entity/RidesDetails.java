package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.ride.pojo.GpsCoordinates;

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
	private Long rideId; // Managed by a sequence generator

	@Field(name = "ride_details")
	private String rideDetails;

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
	private UUID createdByUserId;

	@Field(name = "updated_by_user_id")
	private UUID updatedByUserId;

	@Field(name = "ride_fare")
	private String rideFare;

}
