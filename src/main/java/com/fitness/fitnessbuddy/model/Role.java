package com.fitness.fitnessbuddy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This creates the roles collection and role class along with providing all constructors, getters and setters for the
 * role class
 */
@Document(collection = "roles")
public class Role {

    private String role;

    public Role() {}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}