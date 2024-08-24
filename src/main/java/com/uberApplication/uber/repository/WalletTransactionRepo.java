package com.uberApplication.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.WalletTransaction;

@Repository
public interface WalletTransactionRepo extends JpaRepository<WalletTransaction , Long> {
    
}
