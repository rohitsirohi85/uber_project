package com.uberApplication.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.Rider;

@Repository
public interface RiderRepo extends JpaRepository<Rider , Long> {
    
}
