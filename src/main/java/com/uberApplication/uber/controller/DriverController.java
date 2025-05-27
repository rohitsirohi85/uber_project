package com.uberApplication.uber.controller;

import com.uberApplication.uber.DTO.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.uberApplication.uber.services.DriverService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@Secured("ROLE_DRIVER")  // so only drivers can access these methods
public class DriverController {

    private final DriverService driverService;
    
          @PostMapping("/acceptRide/{rideRequestId}")
          public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){
               return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
          }

    @GetMapping("/rideRequests")
    public List<PendingRideRequestDto> getAvailableRideRequests() {
        return driverService.getPendingRideRequests();
    }


    @PostMapping("/startRide/{rideRequestId}")
          public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId , @RequestBody RideStartDto rideStartDto ){
               return ResponseEntity.ok(driverService.startRide(rideRequestId , rideStartDto.getOtp()));
          }

          @PostMapping("/endRide/{rideId}")
          public ResponseEntity<RideDto> endRide(@PathVariable Long rideId){
               return ResponseEntity.ok(driverService.endRide(rideId));
          }

    @PostMapping(path="/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping(path="/rateRider")
    public ResponseEntity<RiderDto> rateRider(@RequestBody RatingDto ratingDto ){
        return ResponseEntity.ok(driverService.rateRider(ratingDto.getRideId(), ratingDto.getRating()));
    }

    @GetMapping(path = "/getMyProfile")
    public ResponseEntity<DriverDto> getMyProfile(){
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping(path = "/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue="0") Integer pageOffSet , @RequestParam(defaultValue = "10" , required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest
                .of(pageOffSet, pageSize , Sort.by(Sort.Direction.DESC , "CreatedTime" , "id"));
        return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));
    }




}
