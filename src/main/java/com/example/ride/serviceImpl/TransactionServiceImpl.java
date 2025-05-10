package com.example.ride.serviceImpl;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ride.Crud.TransactionCrud;
import com.example.ride.Crud.UserRegistrationCrud;
import com.example.ride.Entity.RidesDetails;
import com.example.ride.Entity.TransactionDetails;
import com.example.ride.Entity.UserRegistration;
import com.example.ride.Request.TransactionRequest;
import com.example.ride.Response.TransactionResponse;
import com.example.ride.pojo.GenericWebServiceResponse;
import com.example.ride.pojo.RideDetailsDto;
import com.example.ride.pojo.Transaction;
import com.example.ride.pojo.Enums.TransactionStatus;
import com.example.ride.service.TransactionService;
import com.example.ride.util.GenericException;
import com.example.ride.util.NoRecordFoundException;
import com.example.ride.util.ResourceNotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TransactionServiceImpl implements TransactionService {
    
    @Autowired
    private TransactionCrud transactionCrud;

    @Autowired
    private UserRegistrationCrud userRegistrationCrud;
    @Override
    public GenericWebServiceResponse createTransaction(TransactionRequest request)
    {
         log.info(request);
         try {
            if(request.getAmount()==null || request.getTranctionRefrence()==null||request.getTransactionBy()==null)
            {
               throw new ResourceNotFoundException("Missing required Field");
            }
            UserRegistration userDetails = userRegistrationCrud.findById(request.getTransactionBy()).orElseThrow(
					() -> new NoRecordFoundException("User not found for the given ID: " + request.getTransactionBy()));

            TransactionDetails transactionDetails = TransactionDetails.builder()
            .transactionReference(request.getTranctionRefrence())
            .amount(request.getAmount())
            .currency(request.getCurrency())
            .description(request.getDescription())
            .destinationAccount(request.getDestinationAccount())
            .sourceAccount(request.getSourceAccount())
            .transactionBy(request.getTransactionBy())
            .transactionFor(request.getTransactionFor())
            .status(request.getStatus().compareTo("success")==0?TransactionStatus.SUCCESS:TransactionStatus.FAILED)
            .transactionDate(LocalDateTime.now()).build();

            transactionCrud.save(transactionDetails);
            
            TransactionResponse resp = mapToTransactionDetails(transactionDetails,userDetails.getFirstName());
            
            return new GenericWebServiceResponse(true, "Transaction Detaisl Added to the database",resp);
   
         } catch (NoRecordFoundException e) {
            throw new NoRecordFoundException("User not found for the given ID");

         }catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("mandatory fields Missing");
		} catch (Exception e) {
			log.info(e);
			throw new GenericException("An error occurred while creating the trancation");
		}
    }

    @Override
    public GenericWebServiceResponse getTransaction(String referenceString) {
        log.info("Fetching transaction for reference: {}", referenceString);
        
        try {
            // Validate input
            if (referenceString == null || referenceString.trim().isEmpty()) {
                throw new ResourceNotFoundException("Transaction reference number cannot be null or empty");
            }
    
            // Find transaction
            TransactionDetails transactionDetails = transactionCrud.findByTransactionReference(referenceString.trim());
            
            if (transactionDetails == null) {
                throw new NoRecordFoundException("No transaction found for reference number: " + referenceString);
            }
    
            // Get user details for the transaction
            UserRegistration userDetails = userRegistrationCrud.findById(transactionDetails.getTransactionBy())
                .orElseThrow(() -> new NoRecordFoundException("User not found for transaction: " + referenceString));
    
            // Map to response
            TransactionResponse response = mapToTransactionDetails(transactionDetails, userDetails.getFirstName());
            
            return new GenericWebServiceResponse(
                true,
                "Transaction details retrieved successfully",
                response
            );
    
        } catch (ResourceNotFoundException e) {
            log.error("Resource not found error: {}", e.getMessage());
            throw e;
        } catch (NoRecordFoundException e) {
            log.error("No record found error: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error fetching transaction details: {}", e.getMessage(), e);
            throw new GenericException("An error occurred while fetching transaction details: " + e.getMessage());
        }
    }


private TransactionResponse mapToTransactionDetails(TransactionDetails request,String userName) {
		
    TransactionResponse transactionResponse = TransactionResponse.builder()
    .transactionReference(request.getTransactionReference())
    .transactionBy(userName)
    .status(request.getStatus())
    .message("The tranction Data added to Database successfully")
    .details(request).build();

    return transactionResponse;
	}


}
