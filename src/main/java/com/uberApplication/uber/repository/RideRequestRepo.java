package com.uberApplication.uber.repository;

import java.util.List;
import com.uberApplication.uber.entities.RideRequest;
import com.uberApplication.uber.entities.enums.RideRequestStatus;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepo extends JpaRepository<RideRequest, Long> {

    List<RideRequest> findByRideRequestStatus(RideRequestStatus rideRequestStatus);


}