package com.uberApplication.uber.services.impl;

import java.util.List;

import com.uberApplication.uber.services.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.RideDto;
import com.uberApplication.uber.DTO.RideRequestDto;
import com.uberApplication.uber.DTO.RiderDto;
import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.entities.Rider;
import com.uberApplication.uber.entities.User;
import com.uberApplication.uber.entities.enums.RideRequestStatus;
import com.uberApplication.uber.entities.enums.RideStatus;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.repository.RideRequestRepo;
import com.uberApplication.uber.repository.RiderRepo;
import com.uberApplication.uber.services.DriverService;
import com.uberApplication.uber.services.RideService;
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
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

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
      Rider rider = getCurrentRider();
      Ride ride = rideService.getRideById(rideId);

    if (!rider.equals(ride.getRider())) {
        throw new RuntimeException("rider is not owned this ride :"+rideId);
    }

      if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
        throw new RuntimeException("rider can't cancel now , invalid status:"+ride.getRideStatus());
      }
     Ride savedRide =  rideService.updateRideStatus(ride, RideStatus.CANCELLED);
     driverService.updateDriverAvailability(ride.getDriver(), true);
      return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();
        // check if this driver owns this ride or not
        if (!rider.equals(ride.getRider())) {
            throw new RuntimeException("Rider is not owner of this ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new RuntimeException("Ride status is not completed yet, Status:"+ride.getRideStatus());
        }

        return ratingService.rateDriver(ride,rating);
    }

    @Override
    public RiderDto getMyProfile() {
       Rider rider = getCurrentRider();
       return modelMapper.map(rider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider rider = getCurrentRider();
        return rideService.getAllRidesOfRider(rider, pageRequest).map(ride->modelMapper.map(ride, RideDto.class));
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

       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       return riderRepo.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("rider not associate with user with id : "+user.getId()));

    }
}
