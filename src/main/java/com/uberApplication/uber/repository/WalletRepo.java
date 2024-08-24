package com.uberApplication.uber.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.User;
import com.uberApplication.uber.entities.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,Long> {

    Optional<Wallet> findByUser(User user);
    
}
