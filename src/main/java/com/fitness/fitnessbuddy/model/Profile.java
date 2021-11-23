package com.fitness.fitnessbuddy.model;

/**
 * This creates the profile class and provides all constructors, getters and setters for the profile class.
 */
public class Profile {

    private String firstName;

    private String lastName;

    public Profile() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
