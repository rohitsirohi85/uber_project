package com.uberApplication.uber.strategies.impl;


import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.strategies.RideFareCalculationStrategy;

public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
