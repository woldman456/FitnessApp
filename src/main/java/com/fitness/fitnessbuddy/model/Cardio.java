package com.fitness.fitnessbuddy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This creates the cardio document and provides all constructors, getters and setters for the cardio class
 */
@Document(collection = "cardio")
public class Cardio {

    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    private String name;

    private String typeOfWorkOut;

    private Integer heartRate;

    public Cardio() {
    }


    @DBRef
    Workout workout;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfWorkOut() {
        return typeOfWorkOut;
    }

    public void setTypeOfWorkOut(String typeOfWorkOut) {
        this.typeOfWorkOut = typeOfWorkOut;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
