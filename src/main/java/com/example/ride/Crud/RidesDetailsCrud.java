package com.example.ride.Crud;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.RidesDetails;

@Repository
public interface RidesDetailsCrud extends MongoRepository<RidesDetails, String> {
    @Query("{ 'ride_start_location': { $near: { $geometry: { type: 'Point', coordinates: ?0 }, $maxDistance: ?1 } } }")
    List<RidesDetails> findNearbyRides(double[] coordinates, double maxDistanceInMeters);
}
