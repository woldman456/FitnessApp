package com.fitness.fitnessbuddy.service;

import com.fitness.fitnessbuddy.model.User;
import com.fitness.fitnessbuddy.model.request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public User createUser(User user);
    public ResponseEntity<?> loginUser(LoginRequest loginRequest);
    public User findUserByEmailAddress(String emailAddress);
    public ResponseEntity<?> loginAdmin(LoginRequest loginRequest);
    public User createAdmin(User User);


}
