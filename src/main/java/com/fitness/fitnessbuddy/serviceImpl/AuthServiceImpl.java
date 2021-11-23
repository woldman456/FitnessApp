package com.fitness.fitnessbuddy.serviceImpl;

import com.fitness.fitnessbuddy.exception.InformationExistException;
import com.fitness.fitnessbuddy.model.*;
import com.fitness.fitnessbuddy.model.request.LoginRequest;
import com.fitness.fitnessbuddy.model.request.LoginResponse;
import com.fitness.fitnessbuddy.respository.AuthRepository;
import com.fitness.fitnessbuddy.respository.RoleRepository;
import com.fitness.fitnessbuddy.security.JWTUtils;
import com.fitness.fitnessbuddy.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    RoleRepository roleRepository;


    /**
     * This creates the business logic to create a user
     * @param user
     * @return
     */
    @Override
    public User createUser(User user) {
        user.setEmailAddress(user.getEmailAddress());
        user.setUserName(user.getUserName());
        if(authRepository.findByEmail(user.getEmailAddress()) != null||
                authRepository.findByUsername(user.getUserName())!= null){
            throw new InformationExistException ("The user name or email address provided is already in use");
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProfile(user.getProfile());
            user.setMeasurements(user.getMeasurements());
            Role role = new Role();
            role.setRole("user");
            user.setRole(role);
            return authRepository.save(user);
        }
    }

    /**
     * This creates the business logic to login a user
     * @param loginRequest
     * @return
     */
    public ResponseEntity<?> loginUser(LoginRequest loginRequest){
        System.out.println("service calling loginUser ==>");
        // LoginRequest has user email and password
        // we get the user name and password from login end point
        //can be mobile app or website
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(),
                loginRequest.getPassword()));
        System.out.println("authenticationManager complete =====>");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmailAddress());
        // we create the JWT token
        final String JWT = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(JWT));

    }
    //need for login process
    public User findUserByEmailAddress(String email) {
        return authRepository.findByEmail(email);
    }

    /**
     * This creates the business logic to login an admin user
     * @param loginRequest
     * @return
     */
    @Override
    public ResponseEntity<?> loginAdmin(LoginRequest loginRequest) {
        System.out.println("service calling loginAdmin ==>");
        // LoginRequest has user email and password
        // we get the user name and password from login end point
        //can be mobile app or website
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(),
                loginRequest.getPassword()));
        System.out.println("authenticationManager complete =====>");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmailAddress());
        // we create the JWT token
        final String JWT = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(JWT));

    }

    /**
     * this creates the business logic to create an admin user
     * @param user
     * @return
     */
    @Override
    public User createAdmin(User user) {
        user.setEmailAddress(user.getEmailAddress());
        user.setUserName(user.getUserName());
        if(authRepository.findByEmail(user.getEmailAddress()) != null||
                authRepository.findByUsername(user.getUserName())!= null){
            throw new InformationExistException ("The user name or email address provided is already in use");
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProfile(user.getProfile());
            Role role = new Role();
            role.setRole("Trainer");
            user.setRole(role);
            return authRepository.save(user);
        }

    }

}
