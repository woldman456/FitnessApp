package com.fitness.fitnessbuddy.controller;

import com.fitness.fitnessbuddy.model.CaloriePerDay;
import com.fitness.fitnessbuddy.service.CaloriePerDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userName}/calories")
public class CaloriesPerDayController {

    @Autowired
    CaloriePerDayService caloriePerDayService;

    /**
     * This method calls to the user object with the userName and allows the user to set the calories by day
     * @param userName
     * @param caloriePerDay
     * @return
     */
    @PostMapping
    public String saveCalories(@PathVariable(value = "userName") String userName, @RequestBody CaloriePerDay caloriePerDay) {
        System.out.println("calling saveCalories controller =====>");
        return caloriePerDayService.saveCalories(userName, caloriePerDay);}

    /**
     * This method calls to the user object with the userName and allows the user to get the calories by day
     * @param userName
     * @param date
     * @return
     */
    @GetMapping("/{date}")
    public CaloriePerDay findCaloriesByDay(@PathVariable(value = "userName")
                                             String userName, @PathVariable(value = "date") String date) {
        System.out.println("Calling find calories by date ====>");
        return caloriePerDayService.findCaloriesByDay(userName, date);}

    /**
     * This method calls to the user object with the userName and allows the user to edit the calories by day
     * @param userName
     * @param date
     * @param caloriePerDay
     * @return
     */
    @PutMapping("/{date}")
    public CaloriePerDay updateCalories(@PathVariable(value = "userName")
                                         String userName, @PathVariable(value = "date") String date, @RequestBody CaloriePerDay caloriePerDay){
        System.out.println("calling updateCalories controller====>");
        return caloriePerDayService.updateCalories(userName, date, caloriePerDay);
    }
}
