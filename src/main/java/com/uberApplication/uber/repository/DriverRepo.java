package com.uberApplication.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver , Long> {
    
}
