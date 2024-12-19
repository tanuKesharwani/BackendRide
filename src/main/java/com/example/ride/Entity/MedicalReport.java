package com.example.ride.Entity;

import java.io.Serializable;
import java.util.UUID;

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
@Table(name = "medical_report")
public class MedicalReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO for UUID generation
	@Column(name = "medical_id", updatable = false, nullable = false)
	private Long medicalId;

	@Column(name = "user_id")
	private UUID userId;

	@Column(name = "medical_condition")
	private String medical_condition;

}
