package com.fitness.fitnessbuddy.respository;

import com.fitness.fitnessbuddy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthRepository extends MongoRepository<User, String> {

    /**
     * This queries for user email address by email addresss
     * @param email
     * @return
     */
    @Query("{'emailAddress' : ?0}")
    User findByEmail(String email);

    /**
     * This queries for user by userName
     * @param userName
     * @return
     */
    @Query("{'userName': ?0}")
    public User findByUsername(String userName);



}
