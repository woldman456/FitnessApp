package com.fitness.fitnessbuddy.serviceImpl;

import com.fitness.fitnessbuddy.exception.InformationNotFoundException;
import com.fitness.fitnessbuddy.model.User;
import com.fitness.fitnessbuddy.model.Workout;
import com.fitness.fitnessbuddy.respository.UserRepository;
import com.fitness.fitnessbuddy.respository.WorkoutRepository;
import com.fitness.fitnessbuddy.security.MyUserDetails;
import com.fitness.fitnessbuddy.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * This calls to the user object by the userName and allows a user to create/save a workout
     * @param userName
     * @param workout
     * @return
     */
    // http://localhost:9092/users/{username}/workouts
    @Override
    public String saveWorkout(String userName, Workout workout) {
        System.out.println("calling save workout====>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getEmailAddress());
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("This user does not exist");
        } else {
            workout.setCalories(workout.getCalories());
            workout.setDayOfWeek(workout.getDayOfWeek());
            workout.setDuration(workout.getDuration());
            workout.setUser(userObject);
            workoutRepository.save(workout);
            return "workout saved";
        }
    }

    /**
     * This calls to the user object by the userName and lists all workouts
     * @param userName
     * @return
     */
    // http://localhost:9092/users/{username}/workouts
    @Override
    public List<Workout> listAllWorkouts(String userName) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getEmailAddress());
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("This user does not exist");
        } else {
            return workoutRepository.findAll();
        }
    }

    /**
     * This calls to the user object by the userName and lists all workouts by date
     * @param userName
     * @param date
     * @return
     */
    // http://localhost:9092/users/{username}/workouts
    @Override
    public Workout findWorkoutByDate(String userName, String date) {
        System.out.println("Calling find workoutByDate ======>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("User does not exist");
        } else {
            Workout workout = workoutRepository.findByDate(date);
            workout.setUser(userObject);
            return workout;
        }
    }

    /**
     * This calls to the user object by the userName then by date and allows the user to
     * edit workouts on specified date
     * @param userName
     * @param date
     * @param workout
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}
    @Override
    public Workout updateWorkout(String userName, String date, Workout workout) {
        System.out.println("calling update workout =====>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("User does not exist");
        } else {
            workout.setUser(userObject);
            Workout workoutOriginal = workoutRepository.findByDate(date);
            if(workout != null) {
                workoutOriginal.setCalories(workout.getCalories());
                workoutOriginal.setDuration(workout.getDuration());
                workoutOriginal.setDayOfWeek(workout.getDayOfWeek());
                workoutRepository.save(workoutOriginal);
                return workoutOriginal;
            }else{
                throw new InformationNotFoundException("not workout with date " + date + " is present");
            }
        }
    }

    /**
     * This calls to the user object by the userName then by date and allows the user to
     * edit workouts on specified date
     * @param userName
     * @param date
     * @param workout
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}
    @Override
    public String deleteWorkout(String userName, String date) {
        System.out.println("calling delete workout =====>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("User does not exist");
        } else {
            Workout workout = new Workout();
            workout.setUser(userObject);
            workout = workoutRepository.findByDate(date);
            workoutRepository.delete(workout);
            return "workout deleted";
        }
    }
}
