package com.uberApplication.uber.services;

import com.uberApplication.uber.DTO.DriverDto;
import java.util.List;
import com.uberApplication.uber.DTO.RideDto;

public interface DriverService {

    RideDto acceptRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId);

    RideDto endRide(Long rideId);

    RideDto rateRider(Long rideId , Integer rating);

    DriverDto getMyProfile();

    List<RideDto>getAllMyRides();

}
