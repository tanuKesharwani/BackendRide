package com.example.ride.Request;

import java.util.List;

import com.example.ride.pojo.BikeDetailsDto;
import com.example.ride.pojo.GpsCoordinates;
import com.example.ride.pojo.MedicalReportDto;

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

	public static final String SAVE_USER_DETAILS = "/user/save-user-details";

	private String firstName;

	private String lastName;

	private String userName;

	private String emailAddress;

	private String phoneNumber;

	private String gender;

	private String bloodGroup;

	private GpsCoordinates Address;

	private String licenseNumber;

	private Boolean isVarifiedLicense;

	private String licenseFrontImg;

	private String licenseBackImg;

	private List<BikeDetailsDto> BikeDetails;

	private List<MedicalReportDto> medicalReport;

}
