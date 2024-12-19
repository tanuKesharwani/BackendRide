package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bike_details") // Specifies the MongoDB collection name
public class BikeDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String bikeId;

	private String bikeNumber;

	private String rideName;

	private UUID userRegistrationId;

	private String bikeModel;

	private String bikeCc;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date serviceDate;

}
