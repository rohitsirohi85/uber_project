package com.uberApplication.uber.controller;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.OnBoardDriverDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uberApplication.uber.DTO.SignupDto;
import com.uberApplication.uber.DTO.UserDto;
import com.uberApplication.uber.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    
      private final AuthService authService;

      @PostMapping(path = "/signup")
     ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
      }

      @PostMapping(path = "/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId , @RequestBody OnBoardDriverDto onBoardDriverDto){
          return new ResponseEntity<>(authService.onboardNewDriver(userId , onBoardDriverDto.getVehicleId()),HttpStatus.CREATED);
      }

}
