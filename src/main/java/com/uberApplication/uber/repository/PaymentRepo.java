package com.uberApplication.uber.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.Payment;
import com.uberApplication.uber.entities.Ride;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {

   Optional<Payment> findByRide(Ride ride);
    
}
