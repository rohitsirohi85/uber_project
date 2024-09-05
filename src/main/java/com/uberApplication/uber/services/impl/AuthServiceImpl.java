package com.uberApplication.uber.services.impl;

import java.lang.module.ResolutionException;
import java.util.Set;

import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.services.DriverService;
import org.modelmapper.ModelMapper;
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

    @Override
    public String login(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepo.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null)
                throw new RuntimeConflictException("Cannot signup, User already exists with email "+signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
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
    
}
