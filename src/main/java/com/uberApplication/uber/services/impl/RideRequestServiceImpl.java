package com.uberApplication.uber.services.impl;

import org.springframework.stereotype.Service;

import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.repository.RideRequestRepo;
import com.uberApplication.uber.services.RideRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

         private final RideRequestRepo rideRequestRepo;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepo.findById(rideRequestId).orElseThrow(()->new ResourceNotFoundException("Ride request not found with id"+rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepo.findById(rideRequest.getId()).orElseThrow(()->new ResourceNotFoundException("RideRequest not found with id:"+rideRequest.getId()));

        rideRequestRepo.save(rideRequest);
    }
    
}
