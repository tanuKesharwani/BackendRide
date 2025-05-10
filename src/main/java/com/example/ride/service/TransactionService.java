package com.example.ride.service;

import com.example.ride.Request.TransactionRequest;
import com.example.ride.Response.TransactionResponse;
import com.example.ride.pojo.GenericWebServiceResponse;

public interface TransactionService {

    public GenericWebServiceResponse createTransaction(TransactionRequest transactionRequest);

    public GenericWebServiceResponse getTransaction(String refrenceString);

    // public GenericWebServiceResponse getTransctionWithFilter(TransactionResponse transactionResponse);
}
