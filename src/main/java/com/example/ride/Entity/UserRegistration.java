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
@Document(collection = "user_registration")
public class UserRegistration implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private UUID userId;

	@Field(name = "first_name")
	private String firstName;

	@Field(name = "last_name")
	private String lastName;

	@Field(name = "email_address")
	private String emailAddress;

	@Field(name = "phone_number")
	private String phoneNumber;

	private String gender;

	@Field(name = "blood_group")
	private String bloodGroup;

	private GpsCoordinates address;

	@Field(name = "created_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date createdDate;

	@Field(name = "updated_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date updatedDate;

	@Field(name = "device_id")
	private String deviceId;

	@Field(name = "fcm_token")
	private String fcmToken;

	@Field(name = "license_number")
	private String licenseNumber;

}
