package com.uberApplication.uber.services.impl;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.RiderDto;
import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Rating;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.Rider;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.exception.RuntimeConflictException;
import com.uberApplication.uber.repository.DriverRepo;
import com.uberApplication.uber.repository.RatingRepo;
import com.uberApplication.uber.repository.RiderRepo;
import com.uberApplication.uber.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;
    private final DriverRepo driverRepo;
    private final RiderRepo riderRepo;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride,Integer rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepo.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("rating not found for this ride: "+ride.getId()));

         if (ratingObj.getDriverRating()!=null){
             throw new RuntimeConflictException("Driver has already been rated");
         }

        ratingObj.setDriverRating(rating);
        ratingRepo.save(ratingObj);
        double newRating = ratingRepo.findByDriver(driver)
                .stream().mapToDouble(Rating::getDriverRating) // (Rating::getDriverRating) means calling the getDriverRating from Rating entity
                .average().orElse(0.0);
        driver.setRating(newRating);
       Driver savedDriver =  driverRepo.save(driver);
       return  modelMapper.map(savedDriver, DriverDto.class);
    }


    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepo.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("rating not found for this ride: "+ride.getId()));

        if (ratingObj.getRiderRating()!=null){
            throw new RuntimeConflictException("Rider has already been rated");
        }

        ratingObj.setRiderRating(rating);
        ratingRepo.save(ratingObj);
        double newRating = ratingRepo.findByRider(rider)
                .stream().mapToDouble(Rating::getRiderRating) // (Rating::getRiderRating) means calling the getRiderRating from Rating entity
                .average().orElse(0.0);
        rider.setRating(newRating);
       Rider savedRider =  riderRepo.save(rider);
       return modelMapper.map(savedRider, RiderDto.class);
    }
    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepo.save(rating);
    }
}
