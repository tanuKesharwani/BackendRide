package com.example.ride.Entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationCrud extends MongoRepository<UserRegistration, Long>{

}
