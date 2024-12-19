package com.example.ride.Crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.MedicalReport;

@Repository
public interface MedicalReportCrud extends MongoRepository<MedicalReport, Long> {

}
