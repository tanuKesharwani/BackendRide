package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.ride.pojo.GpsCoordinates;
import com.example.ride.pojo.SponserDetails;
import com.example.ride.pojo.Enums.RideTypes;

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

	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	@Field(name = "ride_start_location")
	private GpsCoordinates rideStartLocation;

	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	@Field(name = "ride_end_location")
	private GpsCoordinates rideEndLocation;

	@Field(name = "created_by_user_id")
	private String createdByUserId;

	@Field(name = "updated_by_user_id")
	private String updatedByUserId;

	@Field(name = "ride_fare")
	private String rideFare;

	@Field(name = "ride_type")
	private RideTypes rideType;

	@Field(name = "max_rider_allowed")
	private String maxRiderAlllowed;

	@Field(name = "joined_user")
	private List<String> userId;

	@Field(name = "requested_user")
	private List<String> requestedUser;
	
	@Field(name="stay_points")
	private List<GpsCoordinates> stayPoints;
	
	@Field(name="sponser_details")
	private SponserDetails sponserDetails;

	@Field("coordinates")
    private double[] coordinates;

    // This will be called before saving to MongoDB
    public void initializeGeoLocations() {
        if (rideStartLocation != null) {
            rideStartLocation.initializeGeoJson();
        }
        if (rideEndLocation != null) {
            rideEndLocation.initializeGeoJson();
        }
        if (stayPoints != null) {
            stayPoints.forEach(point -> {
                if (point != null) {
                    point.initializeGeoJson();
                }
            });
        }
    }
}
