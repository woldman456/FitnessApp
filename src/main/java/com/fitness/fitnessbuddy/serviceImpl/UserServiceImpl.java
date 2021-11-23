package com.fitness.fitnessbuddy.serviceImpl;

import com.fitness.fitnessbuddy.exception.InformationNotFoundException;
import com.fitness.fitnessbuddy.model.User;
import com.fitness.fitnessbuddy.model.request.LoginRequest;
import com.fitness.fitnessbuddy.model.request.LoginResponse;
import com.fitness.fitnessbuddy.respository.AuthRepository;
import com.fitness.fitnessbuddy.respository.UserRepository;
import com.fitness.fitnessbuddy.security.JWTUtils;
import com.fitness.fitnessbuddy.security.MyUserDetails;
import com.fitness.fitnessbuddy.security.SecurityConfigurer;
import com.fitness.fitnessbuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * this method gets the user object via username for login
     * @param userName
     * @return
     */
    // http://localhost:9092/users/{username}
    @Override
    public User findByUsername(String userName) {
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
        System.out.println("Testing find user name service level ++++++++++++++++");
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new InformationNotFoundException("No user with " + userName + " found");
        } else {
            return user;
        }
    }

    /**
     * this method gets the user object and allows the user to edit user details
     * @param userName
     * @param user
     * @return
     */
    // http://localhost:9092/users/{username}
    @Override
    public String updateUser(String userName, User user) {
        System.out.println("Calling Update user serviceImpl ============>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getEmailAddress());
        User userObject = userRepository.findUserByEAndEmailAddress(userDetails.getUser().getEmailAddress()).get();

        if (userObject != null) {
            userObject.setId(userObject.getId());
            userObject.setPassword(passwordEncoder.encode(user.getPassword()));
            userObject.setEmailAddress(user.getEmailAddress());
            userObject.setMeasurements(user.getMeasurements());
            userObject.setProfile(user.getProfile());
            userRepository.save(userObject);
        }else{
            throw new InformationNotFoundException("No user with userName " + userName +" found");
        }
        return "updated";
    }

    /**
     * This Method accepts a username to locate a user object to be deleted from the database
     * @param userName
     * @return
     */
    @Override
    public String deleteUser(String userName) {
        System.out.println("calling deleteUser ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getEmailAddress());
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("budget with id " + userName + " not found");
        } else {
            userRepository.delete(userObject);
            return "userName " + userName + " has been successfully deleted";
        }

    }

}
