package com.uberApplication.uber.services;

import com.uberApplication.uber.entities.User;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User not found with email:"+username));
    }

    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found with id :"+id));
    }
}
