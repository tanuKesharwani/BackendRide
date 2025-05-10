package com.example.ride.Response;

import com.example.ride.Entity.RidesDetails;
import com.example.ride.pojo.RideDetailsDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NearByRideRespone {
    RidesDetails ride;
    private Double distanceInKm;
} 
