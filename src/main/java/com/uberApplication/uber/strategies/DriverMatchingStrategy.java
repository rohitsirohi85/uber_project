package com.uberApplication.uber.strategies;

import java.util.List;

import com.uberApplication.uber.DTO.RideRequestDto;
import com.uberApplication.uber.entities.Driver;

public interface DriverMatchingStrategy {
    
          List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);

}
