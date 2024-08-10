package com.uberApplication.uber.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.repository.DriverRepo;
import com.uberApplication.uber.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

          private final DriverRepo driverRepo;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
      return driverRepo.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
    
}
