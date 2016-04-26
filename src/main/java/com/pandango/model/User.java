/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sara
 */
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private String bio;
    private int admin;
    private int loginAttempts;
    private Map<String, Double> movieList = new HashMap<>();
    
    public User() {
        
    }
    public User(String firstName, String lastName, String email, String major, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.major = major;
        this.bio = bio;
        admin = 0;
        loginAttempts = 0;
    }
    
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.major = null;
        this.bio = null;
        admin = 0;
        loginAttempts = 0;
    }
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Map<String, Double> getMovieList() {
        return movieList;
    }

    public void setMovieList(Map<String, Double> movieList) {
        this.movieList = movieList;
    }
    
    
    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }
    
    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
    
    public String toString() {
        return (email);
    }
}
