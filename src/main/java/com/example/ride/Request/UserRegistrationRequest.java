package com.example.ride.Request;

import java.util.Date;
import java.util.UUID;

import com.example.ride.pojo.GpsCoordinates;
import com.example.ride.pojo.UserRegistrationDto;

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
public class UserRegistrationRequest {

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String phoneNumber;

	private String gender;

	private String bloodGroup;

	private GpsCoordinates Address;

}
