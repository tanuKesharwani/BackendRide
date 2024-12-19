package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
@Table(name = "bike_details")
public class BikeDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO for UUID generation
	@Column(name = "bike_id", updatable = false, nullable = false)
	private Long bikeId;

	@Column(name = "bike_number")
	private String bikeNumber;

	@Column(name = "ride_name")
	private String rideName;

	@Column(name = "user_id")
	private UUID userRegistrationId;

	@Column(name = "bike_model")
	private String bikeModel;

	@Column(name = "bike_cc")
	private String bikeCc;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "last_service_time")
	private Date serviceDate;

}
