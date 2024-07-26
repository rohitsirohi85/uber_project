package com.uberApplication.uber.DTO;

import java.time.LocalDateTime;

import org.locationtech.jts.geom.Point;

import com.uberApplication.uber.entities.enums.PaymentMethod;
import com.uberApplication.uber.entities.enums.RideRequestStatus;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {

    private Long id;

     
    private Point pickupLocation;

   
    private Point dropoffLocation;

    
    private LocalDateTime requestedTime;

   
    private RiderDto rider;

    
    private PaymentMethod paymentMethod;

   
    private RideRequestStatus rideRequestStatus;
}
