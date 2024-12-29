package com.example.ride.Crud;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

import com.example.ride.Entity.TransactionDetails;
import com.example.ride.pojo.Enums.TransactionStatus;

@Repository
public interface TransactionCrud extends MongoRepository<TransactionDetails, String> {

    
    TransactionDetails findByTransactionReference(String transactionRefrence);

    TransactionDetails findBytransactionBy(String trancationById);
    List<TransactionDetails>findByStatus(TransactionStatus status);  
} 
