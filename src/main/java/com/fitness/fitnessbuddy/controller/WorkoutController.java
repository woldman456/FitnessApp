package com.fitness.fitnessbuddy.controller;

import com.fitness.fitnessbuddy.model.Workout;
import com.fitness.fitnessbuddy.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userName}/workouts")
public class WorkoutController {

    @Autowired
    WorkoutService workoutService;

    /**
     * This calls to the user object by the userName and allows a user to create/save a workout
     * @param userName
     * @param workout
     * @return
     */
    // http://localhost:9092/users/{username}/workouts
    @PostMapping
    public String saveWorkout(@PathVariable(value = "userName") String userName, @RequestBody Workout workout) {
        System.out.println("calling saveWorkout =====>");
        return workoutService.saveWorkout(userName, workout);}

    /**
     * This calls to the user object by the userName and lists all workouts
     * @param userName
     * @return
     */
    // http://localhost:9092/users/{username}/workouts
    @GetMapping
    public List<Workout> listAllWorkouts(String userName) {
        return workoutService.listAllWorkouts(userName);}

    /**
     * This calls to the user object by the userName and lists all workouts by date
     * @param userName
     * @param date
     * @return
     */
    // http://localhost:9092/users/{username}/workouts
    @GetMapping("/{date}")
    public Workout findWorkoutByDate(@PathVariable(value = "userName")
                                             String userName, @PathVariable(value = "date") String date) {
        return workoutService.findWorkoutByDate(userName, date);}

    /**
     * This calls to the user object by the userName then by date and allows the user to
     * edit workouts on specified date
     * @param userName
     * @param date
     * @param workout
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}
    @PutMapping("/{date}")
    public Workout updateWorkout(@PathVariable(value = "userName")
    String userName, @PathVariable(value = "date") String date, @RequestBody Workout workout){
        System.out.println("calling updateworkout controller====>");
        return workoutService.updateWorkout(userName, date, workout);
    }

    /**
     * This calls to the user object by the userName then by date and allows the user to delete
     * workouts on specified date
     * @param userName
     * @param date
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}
    @DeleteMapping("/{date}")
    public String deleteWorkout(@PathVariable(value = "userName") String userName, @PathVariable(value = "date")
                                 String date){System.out.println("calling delete workout controller ====>");
        return workoutService.deleteWorkout(userName, date);}
}




