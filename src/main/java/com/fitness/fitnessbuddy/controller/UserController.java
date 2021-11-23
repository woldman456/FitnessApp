package com.fitness.fitnessbuddy.controller;

import com.fitness.fitnessbuddy.model.User;
import com.fitness.fitnessbuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * this method gets the user object via username for login
     * @param userName
     * @return
     */
    // http://localhost:9092/users/{username}
    @GetMapping(path = "/users/{userName}")
    public User getUserByUserName(@PathVariable String userName){
        System.out.println("getting user +++++++++++++++++++++");
        return userService.findByUsername(userName);}

    /**
     * this method gets the user object and allows the user to edit user details
     * @param userName
     * @param user
     * @return
     */
    // http://localhost:9092/users/{username}
    @PutMapping(path = "/users/{userName}")
    public String updateUser(@PathVariable (value = "userName") String userName, @RequestBody User user){
        System.out.println("Calling update user controller");
        return userService.updateUser(userName, user);}

    /**
     * this method gets the user object and allows the user to delete user
     * @param userName
     * @return
     */
    // http://localhost:9092/users/{username}
    @DeleteMapping(path = "/users/{userName}")
    public String deleteUser(@PathVariable (value = "userName") String userName){
        System.out.println("Calling delete user ======");
        return userService.deleteUser(userName);}
}
