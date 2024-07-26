package com.uberApplication.uber.strategies;

import com.uberApplication.uber.DTO.RideRequestDto;

public interface RideFareCalculationStrategy {
    
       double calculateFare(RideRequestDto rideRequestDto);

}
