package com.fitness.fitnessbuddy.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




/**
 * This creates the user class and users collection along with the constructors, getters and setters for the user class
 */
@Document(collection = "users")
public class User {


    @Id
    private String id;

    /**
     * this specifies specific criteria for username and password and requires that they are unique values.
     */
    @NotBlank
    @Size(min = 6, max = 20, message="criteria not met")
    @Indexed(unique = true, sparse = true)
    private String userName;

    @NotBlank
    @Size(min = 6, max = 20, message="criteria not met")
    private String password;

    @NotBlank
    @Indexed(unique = true)
    private String emailAddress;

    private Measurements measurements;

    private Profile profile;

    private Role role;

    public User() {}

    public User(String userName, String emailAddress, String password) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Measurements getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Measurements measurements) {
        this.measurements = measurements;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }


}