package com.uberApplication.uber.services;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.UserDto;
import com.uberApplication.uber.DTO.signupDto;

public interface AuthService {
    
       String login(String email , String password);

       UserDto signup(signupDto signupDto);

       DriverDto onBoardNewDriver(Long userId);
}
