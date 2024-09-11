package com.uberApplication.uber.controller;

import com.uberApplication.uber.DTO.*;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.uberApplication.uber.services.AuthService;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    
      private final AuthService authService;

      @PostMapping(path = "/signup")
     ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
      }

      @Secured("ROLE_ADMIN") // only user with role admin can access this
      @PostMapping(path = "/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId , @RequestBody OnBoardDriverDto onBoardDriverDto){
          return new ResponseEntity<>(authService.onboardNewDriver(userId , onBoardDriverDto.getVehicleId()),HttpStatus.CREATED);
      }

      @PostMapping(path = "/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto , HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse){
           String tokens[] = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

          Cookie cookie = new Cookie("token" , tokens[1]);
          cookie.setHttpOnly(true);

          httpServletResponse.addCookie(cookie);
           return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
      }

      @PostMapping(path = "/refresh")    // take refreshToken from cookie and return the fresh accessToken from that
       ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){
          String refreshToken = Arrays.stream(request.getCookies())
                  .filter(cookie -> "refreshToken".equals(cookie.getName()))
                  .findFirst()
                  .map(Cookie::getValue)
                  .orElseThrow(()-> new ResourceNotFoundException("refreshToken not found inside cookie"));

          String accessToken = authService.refreshToken(refreshToken);
          return ResponseEntity.ok(new LoginResponseDto(accessToken));
      }
}
