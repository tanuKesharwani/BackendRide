package com.example.ride.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.ride.service.S3Service;
import com.example.ride.util.GenericException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class S3ServiceImpl implements S3Service {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile file, String userId) {
        try {
            // Generate unique file name
            String fileName = userId + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            
            // Convert MultipartFile to File
            File fileObj = convertMultiPartFileToFile(file);
            
            // Upload to S3
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
            
            // Clean up the temporary file
            fileObj.delete();
            
            // Return the file URL
            return s3Client.getUrl(bucketName, fileName).toString();
            
        } catch (Exception e) {
            log.error("Error uploading file to S3", e);
            throw new GenericException("Failed to upload file: " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String fileUrl) {
        try {
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            s3Client.deleteObject(bucketName, fileName);
        } catch (Exception e) {
            log.error("Error deleting file from S3", e);
            throw new GenericException("Failed to delete file: " + e.getMessage());
        }
    }

    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }
}