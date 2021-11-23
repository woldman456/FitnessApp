package com.fitness.fitnessbuddy.serviceImpl;

import com.fitness.fitnessbuddy.exception.InformationNotFoundException;
import com.fitness.fitnessbuddy.model.CaloriePerDay;
import com.fitness.fitnessbuddy.model.User;
import com.fitness.fitnessbuddy.respository.CaloriePerDayRepository;
import com.fitness.fitnessbuddy.respository.UserRepository;
import com.fitness.fitnessbuddy.security.MyUserDetails;
import com.fitness.fitnessbuddy.service.CaloriePerDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CaloriePerDayServiceImpl implements CaloriePerDayService {

    @Autowired
    CaloriePerDayRepository caloriePerDayRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * This method calls for the user object by the userName and allows the user to set the calories per day
     * @param userName
     * @param caloriePerDay
     * @return
     */
    @Override
    public String saveCalories(String userName, CaloriePerDay caloriePerDay) {
        System.out.println("calling save calories ====>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUser().getEmailAddress());
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("This user does not exist");
        } else {
            caloriePerDay.setCalories(caloriePerDay.getCalories());
            caloriePerDay.setDayOfWeek(caloriePerDay.getDayOfWeek());
            caloriePerDay.setDate(caloriePerDay.getDate());
            caloriePerDay.setUser(userObject);
            caloriePerDayRepository.save(caloriePerDay);
            return "Calories for " + caloriePerDay.getDate() + " set to " + caloriePerDay.getCalories();
        }

    }

    /**
     * This method calls for the user object by the userName and then the by date and allows the user to view calories
     * date
     * @param userName
     * @param date
     * @return
     */
    @Override
    public CaloriePerDay findCaloriesByDay(String userName, String date) {
        System.out.println("Calling find workoutByDate ======>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("User does not exist");
        } else {
            CaloriePerDay caloriePerDay = caloriePerDayRepository.findByDate(date);
            caloriePerDay.setUser(userObject);
            return caloriePerDay;
        }
    }

    /**
     * This method calls to the user object with the userName and then by date and allows the user to update their
     * calories for a specified date
     * @param userName
     * @param date
     * @param caloriePerDay
     * @return
     */
    @Override
    public CaloriePerDay updateCalories(String userName, String date, CaloriePerDay caloriePerDay) {
        System.out.println("calling update workout =====>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userObject = userRepository.findByUsername(userDetails.getUser().getUserName());
        if (userObject == null) {
            throw new InformationNotFoundException("User does not exist");
        } else {
            caloriePerDay.setUser(userObject);
            CaloriePerDay caloriePerDayOriginal = caloriePerDayRepository.findByDate(date);
            if (caloriePerDayOriginal != null) {
                caloriePerDayOriginal.setCalories(caloriePerDay.getCalories());
                caloriePerDayOriginal.setDayOfWeek(caloriePerDay.getDayOfWeek());
                return caloriePerDayRepository.save(caloriePerDayOriginal);
            } else {
                throw new InformationNotFoundException("not workout with date " + date + " is present");
            }
        }
    }
}
