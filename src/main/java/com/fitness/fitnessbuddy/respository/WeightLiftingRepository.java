package com.fitness.fitnessbuddy.respository;

import com.fitness.fitnessbuddy.model.WeightLifting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WeightLiftingRepository extends MongoRepository<WeightLifting, String> {
    @Query("{'liftName': ?0}")
    public WeightLifting findByName(String name);
}
