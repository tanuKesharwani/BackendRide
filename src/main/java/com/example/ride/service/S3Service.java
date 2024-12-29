package com.example.ride.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String uploadFile(MultipartFile file, String userId);
    void deleteFile(String fileUrl);
}