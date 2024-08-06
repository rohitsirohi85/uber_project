package com.uberApplication.uber.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
      UserDto signUp(@RequestBody SignupDto signupDto){
        return authService.signup(signupDto);
      }

}
