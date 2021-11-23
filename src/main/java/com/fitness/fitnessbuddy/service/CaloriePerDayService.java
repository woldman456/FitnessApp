package com.fitness.fitnessbuddy.service;

import com.fitness.fitnessbuddy.model.CaloriePerDay;

/**
 * These methods provide the logic for getting these values from each of the methods for calories per day
 */
public interface CaloriePerDayService {

    public String saveCalories(String userName, CaloriePerDay caloriePerDay);
    public CaloriePerDay findCaloriesByDay(String userName, String date);
    public CaloriePerDay updateCalories(String userName, String date, CaloriePerDay caloriePerDay);
}
