package com.uberApplication.uber.strategies.impl;

import org.springframework.stereotype.Service;

import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.services.DistanceService;
import com.uberApplication.uber.strategies.RideFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER;
    }

}
