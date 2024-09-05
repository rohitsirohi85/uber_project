package com.uberApplication.uber.repository;

import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Rating;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepo extends JpaRepository<Rating , Long> {

    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver Driver);

    Optional<Rating> findByRide(Ride ride);
}
