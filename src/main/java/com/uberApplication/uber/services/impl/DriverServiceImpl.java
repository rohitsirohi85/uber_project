package com.uberApplication.uber.services.impl;

import java.time.LocalDateTime;

import com.uberApplication.uber.DTO.RiderDto;
import com.uberApplication.uber.entities.User;
import com.uberApplication.uber.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepo driverRepo;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final RatingService ratingService;

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
      Driver savedDriver = updateDriverAvailability(currentDriver, false);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDto.class);

    }

    @Override
    public RideDto cancelRide(Long rideId) {
     Ride ride = rideService.getRideById(rideId);
     Driver driver = getCurrentDriver();
     if (!driver.equals(ride.getDriver())) {
        throw new RuntimeException("Driver can't cancel ride ,, he has not driver for this");
       }
       if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
        throw new RuntimeException("Ride cannot cancel , invalid Status:"+ride.getRideStatus());
       }
       rideService.updateRideStatus(ride,RideStatus.CANCELLED);
       updateDriverAvailability(driver, true);
       return modelMapper.map(ride, RideDto.class);
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

       paymentService.createNewPayment(savedRide);
       ratingService.createNewRating(savedRide);

     return modelMapper.map(savedRide, RideDto.class);
       
    }

    @Override
    public RideDto endRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
       Driver driver = getCurrentDriver();

       if (!driver.equals(ride.getDriver())) {
        throw new RuntimeException("Driver can't start ride ,, he has not accept earlier");
       }
       if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
        throw new RuntimeException("Ride status is not ONGOING so it can't ne endedS , Status:"+ride.getRideStatus());
       }
       ride.setEndedAt(LocalDateTime.now());
       Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
       updateDriverAvailability(driver, true);
       paymentService.processPayment(ride);
      return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
       Ride ride = rideService.getRideById(rideId);
       Driver driver = getCurrentDriver();
       // check if this driver owns this ride or not
        if (!driver.equals(ride.getDriver())) {
            throw new RuntimeException("Driver is not owner of this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new RuntimeException("Ride status is not completed yet, Status:"+ride.getRideStatus());
        }

       return ratingService.rateRider(ride,rating);
    }

    @Override
    public DriverDto getMyProfile() {
       Driver driver = getCurrentDriver();
       return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
       Driver driver =getCurrentDriver();
        return rideService.getAllRidesOfDriver(driver, pageRequest).map(ride->modelMapper.map(ride, RideDto.class));
    }

    @Override
    public Driver getCurrentDriver() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     return driverRepo.findByUser(user).orElseThrow(()->new ResourceNotFoundException("driver not found with id:"+user.getId()));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, Boolean available) {
      
       driver.setAvailable(available);
      return driverRepo.save(driver);


    }

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepo.save(driver);
    }

}
