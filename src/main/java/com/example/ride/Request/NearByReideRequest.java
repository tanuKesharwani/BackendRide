package com.example.ride.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NearByReideRequest {
    private Double latitude;
    private Double longitude;
    private Double radiusInKm;  // Optional, defaults to 10
    private String userId;
}
