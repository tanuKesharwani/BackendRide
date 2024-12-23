package com.example.ride.pojo;

import java.util.Date;

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

	public static final String GET_ALL_USER_DETAILS = "/user/get-all-user-details";

	public static final String UPDATE_USER_PROFILE = "/user/update-single-user-profile";

	public static final String GET_SINGLE_USER_DETAILS = "/user/get-single-user-details";

	private String userId;

	private String firstName;

	private String lastName;

	private String userName;

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

	private String password;

}
