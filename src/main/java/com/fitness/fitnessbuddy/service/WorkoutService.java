package com.fitness.fitnessbuddy.service;


import com.fitness.fitnessbuddy.model.Workout;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WorkoutService {

    public String saveWorkout(String userName, Workout workout);
    public List<Workout> listAllWorkouts(String userName);
    public Workout findWorkoutByDate(String userName, String date);
    public Workout updateWorkout(String userName, String date, Workout workout);
    public String deleteWorkout(String userName, String date);
}
