package com.example.ride.Entity;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "medical_report")
public class MedicalReport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long medicalId; // Managed by a sequence generator

	private UUID userId;

	private String medicalCondition;

}
