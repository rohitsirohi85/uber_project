package com.uberApplication.uber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uberApplication.uber.DTO.RideRequestDto;
import com.uberApplication.uber.services.RiderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/rider")
@RequiredArgsConstructor
public class RiderController {

            private final RiderService riderService;

            @PostMapping(path = "/requestRide")
        public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
            return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
        }

}
