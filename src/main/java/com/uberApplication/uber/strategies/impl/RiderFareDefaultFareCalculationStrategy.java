package com.uberApplication.uber.strategies.impl;

import org.springframework.stereotype.Service;

import com.uberApplication.uber.DTO.RideRequestDto;
import com.uberApplication.uber.strategies.RideFareCalculationStrategy;

@Service
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateFare'");
    }
    
}
