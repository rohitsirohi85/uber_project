package com.uberApplication.uber.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uberApplication.uber.DTO.RideRequestDto;
import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.strategies.DriverMatchingStrategy;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMatchingDriver'");
    }
    
}
