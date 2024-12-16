package com.example.ride.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "UserRegistration")
public class UserRegistration {

	@Id
	private Long id;

	private String name;

	private int age;
	
	private String deviceId;
	
	private String fcmToken;

}
