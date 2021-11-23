package com.fitness.fitnessbuddy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This method creates the calories collection and includes the constructors, getters and setters for the calories class
 */
@Document(collection = "calories")
public class CaloriePerDay {
    @Id
    private String id;

    private String dayOfWeek;
    private String calories;

    @Indexed(unique = true, sparse = true)
    private String date;

    @DBRef
    private User user;

    public CaloriePerDay() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
