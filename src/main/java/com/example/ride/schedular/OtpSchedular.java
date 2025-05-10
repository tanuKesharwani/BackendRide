package com.example.ride.schedular;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.ride.Crud.OtpCrud;
import com.example.ride.Entity.OtpVerificationEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OtpSchedular {

    @Autowired
    private OtpCrud otpRepository;
    // 300000
    @Scheduled(fixedRate = 30000) // Runs every 5 minutes
    public void cleanupExpiredOTPs() {
        Date fiveMinutesAgo = new Date(System.currentTimeMillis() - 60000);
        try {
            log.info("Schedular Start");
            // Find expired or used OTPs
            List<OtpVerificationEntity> expiredOtps = otpRepository.findByCreatedAtBefore(fiveMinutesAgo);
            System.out.println("expired otp list"+expiredOtps);
            // Delete found OTPs
            if (!expiredOtps.isEmpty()) {
                log.info("deleting the otp using schedular");
                otpRepository.deleteAll(expiredOtps);
                log.info("Cleaned up {} expired or used OTPs", expiredOtps.size());
            }
        } catch (Exception e) {
            log.error("Error cleaning up expired OTPs", e);
        }
    }
}