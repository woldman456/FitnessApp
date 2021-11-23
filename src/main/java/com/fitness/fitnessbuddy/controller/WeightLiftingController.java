package com.fitness.fitnessbuddy.controller;

import com.fitness.fitnessbuddy.model.WeightLifting;
import com.fitness.fitnessbuddy.service.WeightLiftingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userName}/workouts/{date}/weightlifting")
public class WeightLiftingController {

    @Autowired
    WeightLiftingService weightLiftingService;

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the workout and allows the user to save a weightlifting workout
     * @param userName
     * @param date
     * @param weightLifting
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/weightlifting
    @PostMapping
    public String saveWeightLiftingWorkout(@PathVariable(value = "userName") String userName,
                                    @PathVariable(value = "date") String date,
                                    @RequestBody WeightLifting weightLifting){
        System.out.println("Calling Save Cardio workout");
        return weightLiftingService.saveWeightLiftingWorkout(userName, date, weightLifting);}

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the workout and lists all weightlifting workouts for user by date
     * @param userName
     * @param date
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/weightlifting
    @GetMapping
    public List<WeightLifting> listAllWeightLiftingWorkouts(@PathVariable(value = "userName")
                                                                        String userName, @PathVariable(value = "date") String date){
        return weightLiftingService.listAllWeightLiftingWorkouts(userName, date);}

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the cardio workout and lets the user select the cardio workout by user input workout name
     * @param userName
     * @param date
     * @param liftName
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/weightlifting/{typeOfWorkout}/{name}
    @GetMapping("/{liftName}")
    public WeightLifting findWeightLiftingWorkoutByName(@PathVariable(value = "userName")
                                                  String userName, @PathVariable(value = "date")
                                                  String date, @PathVariable(value = "liftName") String liftName){
        return weightLiftingService.findWeightLiftingWorkoutByName(userName, date, liftName);}

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the weightlifting workout and lets the user select the weightlifting
     * workout by user input workout name then edit the workout details
     * @param userName
     * @param date
     * @param liftName
     * @param weightLifting
     * @return
     */
    // |GET | http://localhost:9092/users/{username}/workouts/{date}/cardio/{typeOfWorkout}/{name}
    @PutMapping("/{liftName}")
    public WeightLifting updateWeightLiftingWorkout (@PathVariable(value = "userName")
                                              String userName, @PathVariable(value = "date")
                                              String date, @PathVariable(value = "liftName") String liftName, @RequestBody WeightLifting weightLifting) {
        return weightLiftingService.updateWeightLiftingWorkout(userName, date, liftName, weightLifting);}

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the weightlifting workout and lets the user select the weightlifting workout by user input
     * workout name then allows the user to delete the selected workout
     * @param userName
     * @param date
     * @param liftName
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/weightlifting/{typeOfWorkout}/{name}
    @DeleteMapping("/{liftName}")
    public String deleteWeightLiftingWorkoutByName(@PathVariable(value = "userName")
                                                    String userName, @PathVariable(value = "date")
                                                    String date, @PathVariable(value = "liftName") String liftName){
        return weightLiftingService.deleteWeightLiftingWorkoutByName(userName, date, liftName);}
}
