package com.uberApplication.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.Ride;
@Repository
public interface RideRepo extends JpaRepository<Ride ,Long>{
    
}
