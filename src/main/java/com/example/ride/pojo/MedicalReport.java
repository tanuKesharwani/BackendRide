package com.example.ride.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalReport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String medicalId;

	private String userId;

	private String medicalCondition;

}
