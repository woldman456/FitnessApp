package com.fitness.fitnessbuddy.service;

import com.fitness.fitnessbuddy.model.Cardio;

import java.util.List;

public interface CardioService {
    public String saveCardioWorkout(String userName, String date, Cardio cardio);
    public List<Cardio> listAllCardioWorkouts(String userName, String date);
    public Cardio findCardioWorkoutByName(String userName, String date, String name);
    public Cardio updateCardioWorkout(String userName, String date, String name, Cardio cardio);
    public String deleteCardioWorkoutByName(String userName, String date, String name);
}
