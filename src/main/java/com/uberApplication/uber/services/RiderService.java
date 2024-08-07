package com.uberApplication.uber.services;

import java.util.List;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.RideDto;
import com.uberApplication.uber.DTO.RideRequestDto;
import com.uberApplication.uber.DTO.RiderDto;
import com.uberApplication.uber.entities.Rider;
import com.uberApplication.uber.entities.User;

public interface RiderService {

     RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);


    DriverDto rateDriver(Long rideId , Integer rating);

    RiderDto getMyProfile();

    List<RideDto>getAllMyRides();

    Rider createNewRider(User user);
    
}