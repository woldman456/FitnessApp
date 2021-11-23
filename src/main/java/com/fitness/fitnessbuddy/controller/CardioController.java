package com.fitness.fitnessbuddy.controller;

import com.fitness.fitnessbuddy.model.Cardio;
import com.fitness.fitnessbuddy.service.CardioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users/{userName}/workouts/{date}/cardio")
public class CardioController {

    @Autowired
    CardioService cardioService;

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the workout and allows the user to save a cardio workout
     * @param userName
     * @param date
     * @param cardio
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/cardio
    @PostMapping
    public String saveCardioWorkout(@PathVariable(value = "userName") String userName,
                                    @PathVariable(value = "date") String date,
                                    @RequestBody Cardio cardio){
        System.out.println("Calling Save Cardio workout");
        return cardioService.saveCardioWorkout(userName, date, cardio);
    }

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the workout and lists all cardio workouts for user by date
     * @param userName
     * @param date
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/cardio
    @GetMapping
    public List<Cardio> listAllCardioWorkouts(@PathVariable(value = "userName")
                                                          String userName, @PathVariable(value = "date") String date){
        return cardioService.listAllCardioWorkouts(userName, date);
    }

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the cardio workout and lets the user select the cardio workout by user input workout name
     * @param userName
     * @param date
     * @param name
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/cardio/{typeOfWorkout}/{name}
    @GetMapping("/{name}")
    public Cardio findCardioWorkoutByName(@PathVariable(value = "userName")
                                          String userName, @PathVariable(value = "date")
            String date, @PathVariable(value = "name") String name){
        return cardioService.findCardioWorkoutByName(userName, date, name);
    }

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the cardio workout and lets the user select the cardio workout by user input workout name
     * then edit the workout details
     * @param userName
     * @param date
     * @param name
     * @param cardio
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/cardio/{typeOfWorkout}/{name}
    @PutMapping("/{name}")
    public Cardio updateCardioWorkout(@PathVariable(value = "userName")
                                              String userName, @PathVariable(value = "date")
                                              String date, @PathVariable(value = "name") String name, @RequestBody Cardio cardio){
        return cardioService.updateCardioWorkout(userName, date, name, cardio);
}

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the cardio workout and lets the user select the cardio workout by user input workout name
     * then allows the user to delete the selected workout
     * @param userName
     * @param date
     * @param name
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/cardio/{typeOfWorkout/{name}
    @DeleteMapping("/{name}")
    public String deleteCardioWorkoutByName(@PathVariable(value = "userName")
                                                  String userName, @PathVariable(value = "date")
                                                  String date, @PathVariable(value = "name") String name){
        return cardioService.deleteCardioWorkoutByName(userName, date, name);
    }

}
