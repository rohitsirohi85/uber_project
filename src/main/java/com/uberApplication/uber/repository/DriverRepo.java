package com.uberApplication.uber.repository;

import java.util.List;
import java.util.Optional;

import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.entities.User;
import com.uberApplication.uber.entities.enums.RideRequestStatus;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.Driver;


//ST_Distance(Point1 , Point2)  ..give the distance between two points
//ST_DWithin(Point1 , 10000)    ..give the distance between point and given distance

@Repository
public interface DriverRepo extends JpaRepository<Driver, Long> {


    Optional<Driver> findByUser(User user);
}