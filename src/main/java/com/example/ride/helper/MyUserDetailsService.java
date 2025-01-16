package com.example.ride.helper;


import com.example.ride.Crud.UserRegistrationCrud;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRegistrationCrud userRegistrationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.ride.Entity.UserRegistration user = userRegistrationRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(user.getUserId(),"", new ArrayList<>());
    }
}
