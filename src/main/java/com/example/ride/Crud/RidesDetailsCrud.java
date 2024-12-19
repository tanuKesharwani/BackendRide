package com.example.ride.Crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.RidesDetails;

@Repository
public interface RidesDetailsCrud extends JpaRepository<RidesDetails, Long> {

}
