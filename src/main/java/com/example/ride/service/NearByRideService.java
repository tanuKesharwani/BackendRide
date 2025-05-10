package com.example.ride.service;

import com.example.ride.Request.NearByReideRequest;
import com.example.ride.pojo.GenericWebServiceResponse;

public interface NearByRideService{

    public GenericWebServiceResponse findNearbyRides(NearByReideRequest request);
}
