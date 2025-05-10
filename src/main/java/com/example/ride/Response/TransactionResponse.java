package com.example.ride.Response;


import com.example.ride.Entity.TransactionDetails;
import com.example.ride.pojo.Enums.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private String transactionReference;
    private TransactionStatus status;
    private String message;
    private TransactionDetails details;
    private String transactionBy;
}
