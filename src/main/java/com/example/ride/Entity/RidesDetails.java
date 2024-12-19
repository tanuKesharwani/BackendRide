package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.ride.pojo.GpsCoordinates;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rides_details")
public class RidesDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO for UUID generation
	@Column(name = "ride_id", updatable = false, nullable = false)
	private Long rideId;

	@Column(name = "ride_details")
	private String rideDetails;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "updated_date")
	private Date updatedDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "ride_start_time", nullable = false)
	private Date rideStartTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "ride_ends_time")
	private Date rideEndTime;

	@Column(name = "ride_start_location")
	@JdbcTypeCode(SqlTypes.JSON)
	private GpsCoordinates rideStartLocation;

	@Column(name = "ride_end_location")
	@JdbcTypeCode(SqlTypes.JSON)
	private GpsCoordinates rideEndLocation;

	@Column(name = "created_by_user_id")
	private UUID createdByUserId;

	@Column(name = "updated_by_user_id")
	private UUID updatedByUserId;

	@Column(name = "ride_fare")
	private String rideFare;

}
