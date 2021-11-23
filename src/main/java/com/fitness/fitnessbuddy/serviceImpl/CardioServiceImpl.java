package com.fitness.fitnessbuddy.serviceImpl;

import com.fitness.fitnessbuddy.exception.InformationNotFoundException;
import com.fitness.fitnessbuddy.model.Cardio;
import com.fitness.fitnessbuddy.model.User;
import com.fitness.fitnessbuddy.model.Workout;
import com.fitness.fitnessbuddy.respository.CardioRepository;
import com.fitness.fitnessbuddy.respository.UserRepository;
import com.fitness.fitnessbuddy.respository.WorkoutRepository;
import com.fitness.fitnessbuddy.security.MyUserDetails;
import com.fitness.fitnessbuddy.service.CardioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardioServiceImpl implements CardioService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardioRepository cardioRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    /**
     *
     * this method pulls the userName from the user object,
     * pulls the date from the workout and allows the user to save a cardio workout
     * @param userName
     * @param date
     * @param cardio
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/cardio
    @Override
    public String saveCardioWorkout(String userName, String date, Cardio cardio) {
        System.out.println("calling save Cardio workout ServiceImpl====>");
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
                cardio.setWorkout(workout);
                cardio.setHeartRate(cardio.getHeartRate());
                cardio.setName(cardio.getName());
                cardio.setTypeOfWorkOut(cardio.getTypeOfWorkOut());
                cardioRepository.save(cardio);
                return "Cardio workout saved";
            } else {
                throw new InformationNotFoundException("No workout with date " + date +
                        "found for user " + userName);
            }
        }
    }

    /**
     * this method pulls the userName from the user object,
     * pulls the date from the workout and lists all cardio workouts for user by date
     * @param userName
     * @param date
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/cardio
    @Override
    public List<Cardio> listAllCardioWorkouts(String userName, String date) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getEmailAddress());
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("This user does not exist");
        } else {
            Workout workout = workoutRepository.findByDate(date);
            if (workout != null) {
                return cardioRepository.findAll();
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
     * @param name
     * @return
     */
    // http://localhost:9092/users/{username}/workouts/{date}/cardio/{typeOfWorkout}/{name}
    @Override
    public Cardio findCardioWorkoutByName(String userName, String date, String name) {
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
                Cardio cardio = cardioRepository.findByName(name);
                if (cardio == null) {
                    throw new InformationNotFoundException("Cardio workout with " + name + " does not exist.");
                } else {
                    cardio.setWorkout(workout);
                    return cardio;
                }
            }
        }
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
    @Override
    public Cardio updateCardioWorkout(String userName, String date, String name, Cardio cardio) {
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
                Cardio cardioOriginal = cardioRepository.findByName(name);
                if (cardio == null) {
                    throw new InformationNotFoundException("Cardio workout with " + name + " does not exist.");
                } else {
                    cardio.setWorkout(workout);
                    cardioOriginal.setHeartRate(cardio.getHeartRate());
                    cardioOriginal.setTypeOfWorkOut(cardio.getTypeOfWorkOut());
                    cardioRepository.save(cardioOriginal);
                    return cardioOriginal;
                }
            }
        }
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
    @Override
    public String deleteCardioWorkoutByName(String userName, String date, String name) {
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
                Cardio cardio = cardioRepository.findByName(name);
                if (cardio == null) {
                    throw new InformationNotFoundException("Cardio workout with " + name + " does not exist.");
                } else {
                    cardio.setWorkout(workout);
                    cardioRepository.delete(cardio);
                    return "Cardio workout deleted";
                }
            }
        }
    }
}
