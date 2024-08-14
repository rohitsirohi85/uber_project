package com.uberApplication.uber.DTO;

import java.time.LocalDateTime;


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

     
    private PointDto pickupLocation;

   
    private PointDto dropOffLocation;

    
    private PaymentMethod paymentMethod;
    
    
    
    
    
    
    private RiderDto rider;
    private double fare;
    private LocalDateTime requestedTime;
    private RideRequestStatus rideRequestStatus;

   
}
