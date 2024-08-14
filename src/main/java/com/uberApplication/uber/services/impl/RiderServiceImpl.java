package com.uberApplication.uber.services.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.RideDto;
import com.uberApplication.uber.DTO.RideRequestDto;
import com.uberApplication.uber.DTO.RiderDto;
import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.entities.Rider;
import com.uberApplication.uber.entities.User;
import com.uberApplication.uber.entities.enums.RideRequestStatus;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.repository.RideRequestRepo;
import com.uberApplication.uber.repository.RiderRepo;
import com.uberApplication.uber.services.RiderService;
import com.uberApplication.uber.strategies.RideStrategyManager;



@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager strategyManager;
    private final RideRequestRepo rideRequestRepo;
    private final RiderRepo riderRepo;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider=getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare = strategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepo.save(rideRequest);

       List<Driver> drivers =  strategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);
               // TODO:send the notification to driver to accept ride

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepo.save(rider);
    }

    @Override
    public Rider getCurrentRider() {

       // TODO : implement Spring Security but now us dummy fields

       return riderRepo.findById(1L).orElseThrow(()-> new ResourceNotFoundException("rider not found"));

    }
}
