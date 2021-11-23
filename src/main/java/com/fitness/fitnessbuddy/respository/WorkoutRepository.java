package com.fitness.fitnessbuddy.respository;

import com.fitness.fitnessbuddy.model.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends MongoRepository<Workout, String> {
@Query("{'date': ?0}")
    public Workout findByDate(String date);
}
