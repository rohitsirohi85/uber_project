package com.uberApplication.uber.services;

import com.uberApplication.uber.DTO.DriverDto;

import com.uberApplication.uber.DTO.RiderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uberApplication.uber.DTO.RideDto;
import com.uberApplication.uber.entities.Driver;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId , String otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId , Integer rating);

    DriverDto getMyProfile();

    Page<RideDto>getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, Boolean available);

    Driver createNewDriver(Driver driver);

}
