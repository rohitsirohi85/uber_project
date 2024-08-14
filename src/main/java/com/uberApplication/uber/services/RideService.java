package com.uberApplication.uber.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uberApplication.uber.DTO.RideRequestDto;
import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.entities.enums.RideStatus;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDriver(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequest rideRequest , Driver driver);
    
    Ride updateRideStatus(Ride ride, RideStatus rideStatus);


    Page<Ride>getAllRidesOfRider(Long riderId , PageRequest pageRequest);

    Page<Ride>getAllRidesOfDriver(Long driverId , PageRequest pageRequest);
}
