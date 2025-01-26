package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "OTP_Status")
public class OtpVerificationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id; 

	

	@Field(name = "phone_number")
	private String phoneNumber;

	@Field(name = "OTP")
	private String OTP;

	@Field(name="created_at")
    private Date createdAt;

	
	

}
