package com.fitness.fitnessbuddy.respository;

import com.fitness.fitnessbuddy.model.Cardio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardioRepository extends MongoRepository <Cardio, String>{
    /**
     * This queries the cardio workout by name
     * @param name
     * @return
     */
    @Query("{'name': ?0}")
    public Cardio findByName(String name);
}
