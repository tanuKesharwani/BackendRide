package com.example.ride.Crud;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.UserRegistration;

@Repository
public interface UserRegistrationCrud extends MongoRepository<UserRegistration,UUID>{

}
