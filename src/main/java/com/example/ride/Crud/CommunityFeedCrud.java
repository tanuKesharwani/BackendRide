package com.example.ride.Crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ride.Entity.CommunityFeedEntity;
import java.util.List;
@Repository
public interface CommunityFeedCrud extends MongoRepository<CommunityFeedEntity, String> {

    List<CommunityFeedEntity> findAllByUserId(String userId);
} 