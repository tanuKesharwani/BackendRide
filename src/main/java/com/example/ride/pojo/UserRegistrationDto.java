package com.example.ride.pojo;

import java.util.Date;
import java.util.UUID;

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
public class UserRegistrationDto {

	private UUID userId;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private String phoneNumber;

	private String gender;

	private String bloodGroup;

	private GpsCoordinates Address;

	private Date createdDate;

	private Date updatedDate;

	private String deviceId;

	private String fcmToken;

	private String licenseNumber;

}
