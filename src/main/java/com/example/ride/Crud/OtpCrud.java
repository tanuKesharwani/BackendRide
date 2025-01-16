package com.example.ride.Crud;

import org.springframework.stereotype.Repository;

import com.example.ride.Entity.OtpVerificationEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface OtpCrud extends MongoRepository<OtpVerificationEntity, Long>{
    // Optional<OtpVerificationEntity> findByPhoneNumberAndIsUsedFalse(String phoneNumber);
    // Optional<OtpVerificationEntity> findByEmailAndOtpValueAndIsUsedFalse(String email, String otpValue);
    List<OtpVerificationEntity> findByCreatedAtBefore(Date date);
    Optional<OtpVerificationEntity>findByPhoneNumber(String phoneNumber);
} 
