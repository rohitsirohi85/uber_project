package com.uberApplication.uber.services.impl;

import org.springframework.stereotype.Service;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.UserDto;
import com.uberApplication.uber.DTO.signupDto;
import com.uberApplication.uber.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public UserDto signup(signupDto signupDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
    }

    @Override
    public DriverDto onBoardNewDriver(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onBoardNewDriver'");
    }
    
}
