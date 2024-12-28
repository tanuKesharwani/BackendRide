package com.example.ride.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    
    @Id
    private String id;
    
    private String transactionReference;
    private BigDecimal amount;
    private String currency;
    private String sourceAccount;
    private String destinationAccount;
    private String transactionType;
    private TransactionStatus status;
    private LocalDateTime transactionDate;
    private String description;
    private String transactionBy; //userID
    private String transactionFor; //premium account , join Ride , Create Ride , Join Contest

}
