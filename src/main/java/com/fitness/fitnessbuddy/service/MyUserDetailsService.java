package com.fitness.fitnessbuddy.service;

import com.fitness.fitnessbuddy.model.User;
import com.fitness.fitnessbuddy.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthService authService;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = authService.findUserByEmailAddress(email);
        return new MyUserDetails(user);
    }

}
