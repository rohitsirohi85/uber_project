package com.uberApplication.uber.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.RatingDto;
import com.uberApplication.uber.DTO.RideDto;
import com.uberApplication.uber.DTO.RideRequestDto;
import com.uberApplication.uber.DTO.RiderDto;
import com.uberApplication.uber.services.RiderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/riders")
@RequiredArgsConstructor
@Secured("ROLE_RIDER") // so these method can only be access by rider
public class RiderController {

            private final RiderService riderService;

            @PostMapping(path = "/requestRide")
        public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
            return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
        }

        @PostMapping(path="/cancelRide/{rideId}")
        public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
             return ResponseEntity.ok(riderService.cancelRide(rideId));
        }

        @PostMapping(path="/rateDriver")
        public ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto ratingDto ){
           return ResponseEntity.ok(riderService.rateDriver(ratingDto.getRideId(), ratingDto.getRating()));
        }

        @GetMapping(path = "/getMyProfile")
        public ResponseEntity<RiderDto> getMyProfile(){
            return ResponseEntity.ok(riderService.getMyProfile());
        }

        @GetMapping(path = "/getMyRides")
        public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue="0") Integer pageOffSet , @RequestParam(defaultValue = "10" , required = false) Integer pageSize){
             PageRequest pageRequest = PageRequest.of(pageOffSet, pageSize , Sort.by(Sort.Direction.DESC , "CreatedTime" , "id"));
             return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
        }

}
