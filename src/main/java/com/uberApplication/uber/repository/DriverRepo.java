package com.uberApplication.uber.repository;

import java.util.List;
import java.util.Optional;

import com.uberApplication.uber.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.Driver;


//ST_Distance(Point1 , Point2)  ..give the distance between two points
//ST_DWithin(Point1 , 10000)    ..give the distance between point and given distance

@Repository
public interface DriverRepo extends JpaRepository<Driver, Long> {
    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
            "FROM driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickupLocation);


    @Query(value = "SELECT d.* " + 
                    "FROM driver d " + 
                     "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) " + 
                       "ORDER By d.rating DESC " + 
                       "LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearbyTopRatedDrivers(Point pickupLocation);

    Optional<Driver> findByUser(User user);
}
