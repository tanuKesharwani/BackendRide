package com.example.ride.controller;

import static com.example.ride.Request.TransactionRequest.CREATE_TRANSACTION;
import static com.example.ride.Request.TransactionRequest.GET_TRANSACTION;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ride.Request.TransactionRequest;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.service.TransactionService;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(CREATE_TRANSACTION)
    public GenericWebServiceResponse createNewTransaction(@Valid @RequestBody TransactionRequest transactionRequest){
        return transactionService.createTransaction(transactionRequest);
    }
    @GetMapping(GET_TRANSACTION)
    public GenericWebServiceResponse getTransactionDetails(@RequestBody TransactionRequest transactionRequest){
        return transactionService.getTransaction(transactionRequest.getTranctionRefrence());
    }

}
