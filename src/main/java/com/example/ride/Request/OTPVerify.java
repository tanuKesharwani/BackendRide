package com.example.ride.Request;



import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// import Javax.validation.N
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class OTPVerify {
    private String phoneNumber;
    private String otp;
    private String firstName;
    private String lastName;
    private String userName;
    private MultipartFile profileImage;


}
