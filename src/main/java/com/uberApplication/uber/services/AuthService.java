package com.uberApplication.uber.services;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.UserDto;
import com.uberApplication.uber.DTO.SignupDto;

public interface AuthService {
    
       String[] login(String email , String password);

       UserDto signup(SignupDto signupDto);

       DriverDto onboardNewDriver(Long userId , String vehicleId);

    String refreshToken(String refreshToken);
}
