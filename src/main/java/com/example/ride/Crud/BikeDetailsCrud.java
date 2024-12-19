package com.example.ride.Crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.BikeDetails;

@Repository
public interface BikeDetailsCrud extends JpaRepository<BikeDetails, Long> {

}
