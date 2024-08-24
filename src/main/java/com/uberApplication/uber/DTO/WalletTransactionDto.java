package com.uberApplication.uber.DTO;

import java.time.LocalDateTime;
import com.uberApplication.uber.entities.enums.TransactionMethod;
import com.uberApplication.uber.entities.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionDto {
     private Long id; 

    private double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

 
    private RideDto ride;

    private String transactionId;


    private WalletDto wallet;


    private LocalDateTime timeStamp;

}
