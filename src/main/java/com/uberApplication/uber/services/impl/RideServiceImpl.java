package com.uberApplication.uber.services.impl;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.entities.Rider;
import com.uberApplication.uber.entities.enums.RideRequestStatus;
import com.uberApplication.uber.entities.enums.RideStatus;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.repository.RideRepo;
import com.uberApplication.uber.services.RideRequestService;
import com.uberApplication.uber.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepo rideRepo;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
     return rideRepo.findById(rideId).orElseThrow(()-> new ResourceNotFoundException("Ride not found with id:"+rideId));
    }

 

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateOTP());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return rideRepo.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
      ride.setRideStatus(rideStatus);
       return rideRepo.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
      return rideRepo.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
       return rideRepo.findByDriver(driver,pageRequest);
    }

    public String generateOTP(){
        Random random = new Random();
        int otpInt = random.nextInt(10000); // 0 to 9999
        return String.format("%04d", otpInt);

    }
    
}
