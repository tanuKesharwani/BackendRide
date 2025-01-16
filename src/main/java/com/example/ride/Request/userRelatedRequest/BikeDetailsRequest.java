package com.example.ride.Request.userRelatedRequest;


import java.util.Date;
import java.util.List;

import com.example.ride.pojo.BikeDetails;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BikeDetailsRequest {

    public static final String ADD_VEHICLE_STRING = "/auth/user/addVehicle";
    public static final String GET_ALL_VEHICLE_STRING ="/auth/user/getVehicle";

    List<BikeDetails> bikeDetails;


    
}
