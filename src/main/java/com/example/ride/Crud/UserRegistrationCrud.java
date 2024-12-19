package com.example.ride.Crud;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.UserRegistration;

@Repository
public interface UserRegistrationCrud extends JpaRepository<UserRegistration,UUID>{

}
