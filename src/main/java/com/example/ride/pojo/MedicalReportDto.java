package com.example.ride.pojo;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalReportDto {

	private Long medicalId;

	private UUID userId;

	private String medical_condition;

}
