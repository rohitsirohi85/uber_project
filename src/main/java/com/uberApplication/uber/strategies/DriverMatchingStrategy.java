package com.uberApplication.uber.strategies;

import java.util.List;

import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.RideRequest;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
