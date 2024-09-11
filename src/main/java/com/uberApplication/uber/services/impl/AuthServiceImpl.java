package com.uberApplication.uber.services.impl;

import java.lang.module.ResolutionException;
import java.util.Set;

import com.uberApplication.uber.Security.JwtService;
import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.services.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uberApplication.uber.DTO.DriverDto;
import com.uberApplication.uber.DTO.UserDto;
import com.uberApplication.uber.DTO.SignupDto; 
import com.uberApplication.uber.entities.User;
import com.uberApplication.uber.entities.enums.Role;
import com.uberApplication.uber.exception.RuntimeConflictException;
import com.uberApplication.uber.repository.UserRepo;
import com.uberApplication.uber.services.AuthService;
import com.uberApplication.uber.services.RiderService;
import com.uberApplication.uber.services.WalletService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

      private final RiderService riderService;
      private final UserRepo userRepo;
      private final ModelMapper modelMapper;
      private final WalletService walletService;
      private final DriverService driverService;
      private final PasswordEncoder passwordEncoder;
      private final AuthenticationManager authenticationManager;
      private final JwtService jwtService;

    @Override
    public String[] login(String email, String password) {



        Authentication authentication =  authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(email,password)
         );

         User user = (User) authentication.getPrincipal();
        String accessToken =  jwtService.generateAccessTokens(user);
        String refreshToken =  jwtService.generateRefreshTokens(user);

        return new String[]{accessToken,refreshToken};

    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepo.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null)
                throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepo.save(mappedUser);

//        create user related entities
        riderService.createNewRider(savedUser);
         walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }


  

    @Override
    public DriverDto onboardNewDriver(Long userId , String vehicleId) {
       User user = userRepo.findById(userId).orElseThrow(()-> new ResolutionException("user not found with id :"+userId));

       if (user.getRoles().contains(Role.DRIVER)){
           throw new RuntimeConflictException("user with id :"+ userId + "is already a driver");
       }

       Driver createDriver = Driver.builder()
               .user(user)
               .rating(0.0)
               .vehicleId(vehicleId)
               .available(true)
               .build();
       user.getRoles().add(Role.DRIVER);
       userRepo.save(user);
        Driver saveDriver =  driverService.createNewDriver(createDriver);
         return modelMapper.map(saveDriver,DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {  // method to create and return the access token while use refresh api
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found with id:"+userId));
        return jwtService.generateAccessTokens(user);
    }

}
