package com.example.ride.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.ride.pojo.Enums.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class TransactionDetails implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    private String transactionid;
    
    @Field(name="transaction_refrence_id")
    private String transactionReference;

    @Field(name="tranction_amount")
    private BigDecimal amount;

    @Field(name="currency_user")
    private String currency;

    @Field(name="source_account")
    private String sourceAccount;

    @Field(name="destination_account")
    private String destinationAccount;

    @Field(name="tranction_type")
    private String transactionType;

    @Field(name="tranaction_type")
    private TransactionStatus status;

    @Field(name="tranction_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime transactionDate;

    @Field(name="tranction_description")
    private String description;

    @Field(name="tranction_by_user_id")
    private String transactionBy; //userID

    @Field(name="tranction_for")
    private String transactionFor; //premium account , join Ride , Create Ride , Join Contest

}
