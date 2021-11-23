package com.fitness.fitnessbuddy.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This creates the weightlifting collections and class along with constructors, getters and setters for the
 * weightlifting class
 */
@Document(collection = "weightLifting")
public class WeightLifting {


    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    private String liftName;

    private String liftType;

    private Integer numberOfSets;

    private Integer numberOfReps;

    private Integer weightPerSet;

    @DBRef
    private Workout workout;

    public WeightLifting() {}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getLiftName() {return liftName;}

    public void setLiftName(String liftName) {this.liftName = liftName;}

    public String getLiftType() {return liftType;}

    public void setLiftType(String liftType) {this.liftType = liftType;}

    public Integer getNumberOfSets() {return numberOfSets;}

    public void setNumberOfSets(Integer numberOfSets) {this.numberOfSets = numberOfSets;}

    public Integer getNumberOfReps() {return numberOfReps;}

    public void setNumberOfReps(Integer numberOfReps) {this.numberOfReps = numberOfReps;}

    public Integer getWeightPerSet() {return weightPerSet;}

    public void setWeightPerSet(Integer weightPerSet) {this.weightPerSet = weightPerSet;}

    public Workout getWorkout() {return workout;}

    public void setWorkout(Workout workout) {this.workout = workout;}
}
