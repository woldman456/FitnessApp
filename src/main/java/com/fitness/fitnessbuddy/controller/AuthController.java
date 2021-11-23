package com.fitness.fitnessbuddy.controller;

import com.fitness.fitnessbuddy.model.User;
import com.fitness.fitnessbuddy.model.request.LoginRequest;
import com.fitness.fitnessbuddy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;


    /**
     * This method creates a user and returns an object of a registered user
     * @param user
     * @return
     */
    // http://localhost:9092/auth/users/register
    @PostMapping(path = "/auth/users/register")
    public User createUser(@RequestBody User user){
        System.out.println("calling User ==============>");
        return authService.createUser(user);
    }

    /**
     * this method runs the loginRequest and logs in a user
     * @param loginRequest
     * @return
     */
    // http://localhost:9092/auth/users/login
    @PostMapping("/auth/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("calling loginUser ==>");
        return authService.loginUser(loginRequest);
    }

    /**
     * This method creates an admin and returns an object of a registered admin
     * @param trainer
     * @return
     */
    // http://localhost:9092/auth/admin/register
    @PostMapping(path = "/auth/admin/register")
    public User createAdmin(@RequestBody User user) {
        System.out.println("calling User ==============>");
        return authService.createAdmin(user);
    }

    /**
     * this method runs the loginRequest and logs in an admin
     * @param loginRequest
     * @return
     */
    // http://localhost:9092/auth/admin/login
    @PostMapping("/auth/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginRequest loginRequest) {
        System.out.println("calling loginUser ==>");
        return authService.loginAdmin(loginRequest);
    }

}
