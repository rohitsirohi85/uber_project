package com.uberApplication.uber.strategies.impl;


import org.springframework.stereotype.Service;

import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.services.DistanceService;
import com.uberApplication.uber.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;
    private static final double SURGE_FACTOR=2;  // define the surge factor prices 

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
