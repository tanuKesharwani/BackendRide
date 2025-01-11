package com.example.ride.Request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


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

public class NotificationRequest {
    private String fcmToken;
}
