package com.example.ride.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.ride.pojo.GpsCoordinates;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_registration")
public class UserRegistration implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO for UUID generation
	@Column(name = "user_registration_id", updatable = false, nullable = false)
	private UUID userRegistrationId;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "email_address", length = 320)
	private String emailAddress;

	@Column(name = "phone_number", length = 11)
	private String phoneNumber;

	@JdbcTypeCode(SqlTypes.JSON)
	private GpsCoordinates Address;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "device_id")
	private String deviceId;

	@Column(name = "fcm_token")
	private String fcmToken;

}