package com.example.ride.Crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.UserRegistration;

@Repository
public interface UserRegistrationCrud extends MongoRepository<UserRegistration, String> {

	Optional<UserRegistration> findByPhoneNumber(String phoneNumber);
	List<UserRegistration> findByUserIdIn(List<String> userIds);

}
