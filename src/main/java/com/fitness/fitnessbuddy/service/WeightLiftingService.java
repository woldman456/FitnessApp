package com.fitness.fitnessbuddy.service;

import com.fitness.fitnessbuddy.model.WeightLifting;

import java.util.List;

public interface WeightLiftingService {
    public String saveWeightLiftingWorkout(String userName, String date, WeightLifting weightLifting);
    public List<WeightLifting> listAllWeightLiftingWorkouts(String userName, String date);
    public WeightLifting findWeightLiftingWorkoutByName(String userName, String date, String liftName);
    public WeightLifting updateWeightLiftingWorkout(String userName, String date, String liftName, WeightLifting weightLifting);
    public String deleteWeightLiftingWorkoutByName(String userName, String date, String liftName);
}
