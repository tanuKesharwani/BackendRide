package com.example.ride.Crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.BikeDetails;

@Repository
public interface BikeDetailsCrud extends MongoRepository<BikeDetails, String> {

}
