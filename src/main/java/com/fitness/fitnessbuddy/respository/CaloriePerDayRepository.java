package com.fitness.fitnessbuddy.respository;

import com.fitness.fitnessbuddy.model.CaloriePerDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * This method queries the calories per day
 */
public interface CaloriePerDayRepository extends MongoRepository<CaloriePerDay, String> {
    @Query("{'date': ?0}")
    public CaloriePerDay findByDate(String date);
}

