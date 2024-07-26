package com.uberApplication.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.RideRequest;

@Repository
public interface RideRequestRepo extends JpaRepository<RideRequest , Long>{
    
}
