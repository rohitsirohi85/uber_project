package com.uberApplication.uber.repository;

import com.uberApplication.uber.entities.enums.RideRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.uber.entities.RideRequest;

import java.util.List;

@Repository
public interface RideRequestRepo extends JpaRepository<RideRequest , Long>{

    List<RideRequest> findByRideRequestStatus(RideRequestStatus rideRequestStatus);
}
