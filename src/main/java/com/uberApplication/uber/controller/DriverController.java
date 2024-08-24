package com.uberApplication.uber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uberApplication.uber.DTO.RideDto;
import com.uberApplication.uber.DTO.RideStartDto;
import com.uberApplication.uber.services.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;
    
          @PostMapping("/acceptRide/{rideRequestId}")
          public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){
               return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
          }

          @PostMapping("/startRide/{rideRequestId}")
          public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId , @RequestBody RideStartDto rideStartDto ){
               return ResponseEntity.ok(driverService.startRide(rideRequestId , rideStartDto.getOtp()));
          }

          @PostMapping("/endRide/{rideId}")
          public ResponseEntity<RideDto> endRide(@PathVariable Long rideId){
               return ResponseEntity.ok(driverService.endRide(rideId));
          }

}
