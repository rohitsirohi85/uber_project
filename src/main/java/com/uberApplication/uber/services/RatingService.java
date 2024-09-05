package com.uberApplication.uber.services;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.RiderDto;
import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.Rider;

public interface RatingService {
    DriverDto rateDriver(Ride ride , Integer rating);
    RiderDto rateRider(Ride ride , Integer rating);

    void createNewRating(Ride ride);
}
