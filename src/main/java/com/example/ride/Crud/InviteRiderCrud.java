package com.example.ride.Crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.InviteRiders;

@Repository
public interface InviteRiderCrud extends JpaRepository<InviteRiders, Long> {

}
