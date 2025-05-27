package com.uberApplication.uber.services;

import com.uberApplication.uber.DTO.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uberApplication.uber.entities.Driver;

import java.util.List;

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

    List<PendingRideRequestDto> getPendingRideRequests();
}
