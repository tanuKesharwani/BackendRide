package com.example.ride.Crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.MedicalReport;

@Repository
public interface MedicalReportCrud extends JpaRepository<MedicalReport, Long> {

}
