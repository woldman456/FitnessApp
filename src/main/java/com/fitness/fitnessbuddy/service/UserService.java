package com.fitness.fitnessbuddy.service;


import com.fitness.fitnessbuddy.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User findByUsername(String username);
    public String updateUser(String userName, User user);
    public String deleteUser(String userName);


}
