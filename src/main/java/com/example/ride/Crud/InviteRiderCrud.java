package com.example.ride.Crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.InviteRiders;

@Repository
public interface InviteRiderCrud extends MongoRepository<InviteRiders, Long> {

}
