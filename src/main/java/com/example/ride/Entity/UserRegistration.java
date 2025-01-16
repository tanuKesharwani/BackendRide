package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.ride.pojo.BikeDetails;
import com.example.ride.pojo.GpsCoordinates;
import com.example.ride.pojo.MedicalReport;
import com.example.ride.pojo.PremiumDetails;

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
	private String userId;

	@Field(name = "first_name")
	private String firstName;

	@Field(name = "last_name")
	private String lastName;

	@Field(name = "username")
	private String userName;

	@Field(name = "email_address")
	private String emailAddress;

	@Field(name = "password")
	private String password;

	@Field(name = "phone_number")
	private String phoneNumber;

	@Field(name = "gender")
	private String gender;

	@Field(name = "blood_group")
	private String bloodGroup;

	@Field(name = "addess")
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

	@Field(name = "is_Varified_license")
	private Boolean isVarifiedLicense;

	@Field(name = "license_Front_img")
	private String licenseFrontImg;

	@Field(name = "license_back_img")
	private String licenseBackImg;
	@Field(name = "profile_image")
	private String profileImage;
	@Field(name="is_premium")
	private Boolean isPremium;

	@Field(name="premium_details")
	private PremiumDetails premiumDetails;
	
	private List<BikeDetails> bikeDetails;

	private List<MedicalReport> medicalReport;

}
