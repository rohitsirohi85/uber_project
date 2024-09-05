package com.uberApplication.uber.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.uberApplication.uber.entities.enums.TransactionMethod;
import com.uberApplication.uber.entities.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "idx_wallet_transactions_wallet" , columnList = "wallet_id"),
        @Index(name = "idx_wallet_transactions_ride" , columnList = "ride_id")
})
public class WalletTransaction {
    
           @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    @ManyToOne
    private Ride ride;

    private String transactionId;

    @ManyToOne
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timeStamp;

}
