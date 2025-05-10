package com.example.ride.pojo;

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

	private String userId;

	private String medicalCondition;

}
