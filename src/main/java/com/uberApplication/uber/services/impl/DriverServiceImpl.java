package com.uberApplication.uber.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.RideDto;
import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.entities.enums.RideRequestStatus;
import com.uberApplication.uber.entities.enums.RideStatus;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.repository.DriverRepo;
import com.uberApplication.uber.services.DriverService;
import com.uberApplication.uber.services.RideRequestService;
import com.uberApplication.uber.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepo driverRepo;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if (!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException("Ride request cannot be accepted , Ride request Status is:"+rideRequest.getRideRequestStatus());
        }
        
        Driver currentDriver=getCurrentDriver();
        if (!currentDriver.getAvailable()) {
            throw new RuntimeException("Driver is not available now");
        }
       currentDriver.setAvailable(false);
       Driver savedDriver = driverRepo.save(currentDriver);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDto.class);

    }

    @Override
    public RideDto cancelRide(Long rideId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelRide'");
    }

    @Override
    public RideDto startRide(Long rideId , String otp) {
       Ride ride = rideService.getRideById(rideId);
       Driver driver = getCurrentDriver();

       if (!driver.equals(ride.getDriver())) {
        throw new RuntimeException("Driver can't start ride ,, he has not accept earlier");
       }
       if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
        throw new RuntimeException("Ride status is not confirmed , Status:"+ride.getRideStatus());
       }
       if (!otp.equals(ride.getOtp())) {
        throw new RuntimeException("otp is not valid:"+otp);
       }

       ride.setStartedAt(LocalDateTime.now());
     Ride savedRide =   rideService.updateRideStatus(ride, RideStatus.ONGOING);
     return modelMapper.map(savedRide, RideDto.class);
       
    }

    @Override
    public RideDto endRide(Long rideId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endRide'");
    }

    @Override
    public RideDto rateRider(Long rideId, Integer rating) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rateRider'");
    }

    @Override
    public DriverDto getMyProfile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMyProfile'");
    }

    @Override
    public List<RideDto> getAllMyRides() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMyRides'");
    }

    @Override
    public Driver getCurrentDriver() {
     return driverRepo.findById(2L).orElseThrow(()->new ResourceNotFoundException("driver not found with id:"+2L));
    }
    
}
