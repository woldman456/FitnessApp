package com.fitness.fitnessbuddy.model;

/**
 * This creates the measurements class and provides all constructors, getters and setters for the measurements class
 */
public class Measurements {

    private String height;

    private Double weight;

    private String bodyFatPercent;

    public Measurements() {}

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBodyFatPercent() {
        return bodyFatPercent;
    }

    public void setBodyFatPercent(String bodyFatPercent) {
        this.bodyFatPercent = bodyFatPercent;
    }
}
