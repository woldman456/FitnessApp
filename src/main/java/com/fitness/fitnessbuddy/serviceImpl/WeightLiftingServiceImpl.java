package com.fitness.fitnessbuddy.serviceImpl;

import com.fitness.fitnessbuddy.exception.InformationNotFoundException;
import com.fitness.fitnessbuddy.model.Cardio;
import com.fitness.fitnessbuddy.model.User;
import com.fitness.fitnessbuddy.model.WeightLifting;
import com.fitness.fitnessbuddy.model.Workout;
import com.fitness.fitnessbuddy.respository.UserRepository;
import com.fitness.fitnessbuddy.respository.WeightLiftingRepository;
import com.fitness.fitnessbuddy.respository.WorkoutRepository;
import com.fitness.fitnessbuddy.security.MyUserDetails;
import com.fitness.fitnessbuddy.service.WeightLiftingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightLiftingServiceImpl implements WeightLiftingService {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WeightLiftingRepository weightLiftingRepository;

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the workout and allows the user to save a weightlifting workout
     * @param userName
     * @param date
     * @param weightLifting
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/weightlifting
    @Override
    public String saveWeightLiftingWorkout(String userName, String date, WeightLifting weightLifting) {
        System.out.println("calling save WeightLifting workout ServiceImpl====>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getEmailAddress());
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("This user does not exist");
        } else {
            Workout workout = new Workout();
            workout.setUser(userObject);
            workout = workoutRepository.findByDate(date);
            if (workout != null) {
                weightLifting.setWorkout(workout);
                weightLifting.setLiftName(weightLifting.getLiftName());
                weightLifting.setWeightPerSet(weightLifting.getWeightPerSet());
                weightLifting.setNumberOfReps(weightLifting.getNumberOfReps());
                weightLifting.setNumberOfSets(weightLifting.getNumberOfSets());
                weightLifting.setLiftType(weightLifting.getLiftType());
                weightLiftingRepository.save(weightLifting);
                return "WeightLifting workout saved";
            } else {
                throw new InformationNotFoundException("No workout with date " + date +
                        "found for user " + userName);
            }
        }
    }

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the workout and allows the user to save a weightlifting workout
     * @param userName
     * @param date
     * @param weightLifting
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/weightlifting
    @Override
    public List<WeightLifting> listAllWeightLiftingWorkouts(String userName, String date) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getEmailAddress());
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("This user does not exist");
        } else {
            Workout workout = workoutRepository.findByDate(date);
            if (workout != null) {
                return weightLiftingRepository.findAll();
            } else {
                throw new InformationNotFoundException("This workout with " + date
                        + "does not exist");
            }
        }
    }

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the cardio workout and lets the user select the cardio workout by user input workout name
     * @param userName
     * @param date
     * @param liftName
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/weightlifting/{typeOfWorkout}/{name}
    @Override
    public WeightLifting findWeightLiftingWorkoutByName(String userName, String date, String liftName) {
        System.out.println("Calling find cardio workout by name ======>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("User does not exist");
        } else {
            Workout workout = workoutRepository.findByDate(date);
            if (workout == null) {
                throw new InformationNotFoundException("Workout for " + date + " does not exist");
            } else {
                workout.setUser(userObject);
                WeightLifting weightLifting = weightLiftingRepository.findByName(liftName);
                if (weightLifting == null) {
                    throw new InformationNotFoundException("Cardio workout with " + liftName + " does not exist.");
                } else {
                    weightLifting.setWorkout(workout);
                    return weightLifting;
                }
            }
        }
    }

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
    @Override
    public WeightLifting updateWeightLiftingWorkout(String userName, String date, String liftName, WeightLifting weightLifting) {
        System.out.println("Calling find cardio workout by name ======>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("User does not exist");
        } else {
            Workout workout = workoutRepository.findByDate(date);
            if (workout == null) {
                throw new InformationNotFoundException("Workout for " + date + " does not exist");
            } else {
                workout.setUser(userObject);
                WeightLifting weightLiftingOriginal = weightLiftingRepository.findByName(liftName);
                if (weightLiftingOriginal == null) {
                    throw new InformationNotFoundException("Cardio workout with " + liftName + " does not exist.");
                } else {
                    weightLifting.setWorkout(workout);
                    weightLiftingOriginal.setLiftType(weightLifting.getLiftType());
                    weightLiftingOriginal.setWeightPerSet(weightLifting.getWeightPerSet());
                    weightLiftingOriginal.setNumberOfReps(weightLifting.getNumberOfReps());
                    weightLiftingOriginal.setWeightPerSet(weightLifting.getWeightPerSet());
                    weightLiftingRepository.save(weightLiftingOriginal);
                    return weightLiftingOriginal;
                }
            }
        }
    }

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
    @Override
    public String deleteWeightLiftingWorkoutByName(String userName, String date, String liftName) {
        System.out.println("Calling find cardio workout by name ======>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("User does not exist");
        } else {
            Workout workout = workoutRepository.findByDate(date);
            if (workout == null) {
                throw new InformationNotFoundException("Workout for " + date + " does not exist");
            } else {
                workout.setUser(userObject);
                WeightLifting weightLifting = weightLiftingRepository.findByName(liftName);
                if (weightLifting == null) {
                    throw new InformationNotFoundException("Cardio workout with " + liftName + " does not exist.");
                } else {
                    weightLifting.setWorkout(workout);
                    weightLiftingRepository.delete(weightLifting);
                    return "Cardio workout deleted";
                }
            }
        }
    }
}
