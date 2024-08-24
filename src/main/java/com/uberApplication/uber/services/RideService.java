package com.uberApplication.uber.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.entities.Rider;
import com.uberApplication.uber.entities.enums.RideStatus;

public interface RideService {

    Ride getRideById(Long rideId);


    Ride createNewRide(RideRequest rideRequest , Driver driver);
    
    Ride updateRideStatus(Ride ride, RideStatus rideStatus);


    Page<Ride>getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride>getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
