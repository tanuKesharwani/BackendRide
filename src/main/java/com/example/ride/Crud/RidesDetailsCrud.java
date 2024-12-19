package com.example.ride.Crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.RidesDetails;

@Repository
public interface RidesDetailsCrud extends MongoRepository<RidesDetails, Long> {

}
