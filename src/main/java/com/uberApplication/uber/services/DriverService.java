package com.uberApplication.uber.services;

import com.uberApplication.uber.DTO.DriverDto;
import java.util.List;
import com.uberApplication.uber.DTO.RideDto;
import com.uberApplication.uber.entities.Driver;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId , String otp);

    RideDto endRide(Long rideId);

    RideDto rateRider(Long rideId , Integer rating);

    DriverDto getMyProfile();

    List<RideDto>getAllMyRides();

    Driver getCurrentDriver();

}
