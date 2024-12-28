package com.example.ride.Request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// import Javax.validation.N
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class TransactionRequest {
    public static final String CREATE_TRANSACTION = "/transaction/create";
    public static final String GET_TRANSACTION = "/transaction";

    private String tranctionRefrence;
    private String status;

    @NotNull(message = "Amount is Required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    private String currency;

    private String sourceAccount;


    private String destinationAccount;
    private String transactionType;

    private String description;

    @NotNull(message = "User Id Required")
    private String transactionBy;

    private String transactionFor; // Premium , Join Ride, etc
}
