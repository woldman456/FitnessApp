package com.fitness.fitnessbuddy.respository;

import com.fitness.fitnessbuddy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'userName': ?0}")
    public User findByUsername(String userName);

    @Query("{'emailAddress': ?0}")
    public User findByEmail(String emailAddress);

    @Query("{'emailAddress': ?0}")
    public Optional<User> findUserByEAndEmailAddress(String emailAddress);



}
