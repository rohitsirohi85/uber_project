package com.uberApplication.uber.DTO;

import java.time.LocalDateTime;

import org.locationtech.jts.geom.Point;

import com.uberApplication.uber.entities.enums.PaymentMethod;
import com.uberApplication.uber.entities.enums.RideStatus;

import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
     private Long id;

   
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation;

    
    private LocalDateTime createdTime;  // when driver accept ride


    private RiderDto rider;


    private DriverDto driver;

    
    private PaymentMethod paymentMethod;

 
    private RideStatus rideStatus;

    private String otp;

    private double fair;

    private LocalDateTime startedAt;  // when driver start the ride

    private LocalDateTime endedAt; 
}
