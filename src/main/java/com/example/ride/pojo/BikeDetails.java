package com.example.ride.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String bikeId;

	private String bikeNumber;

	private String userId;

	private String bikeModel;

	private String bikeCc;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date serviceDate;

}
