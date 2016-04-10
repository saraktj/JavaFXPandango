/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango.model;

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
    private Map<String, Integer> movieList;
    
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

    public Map<String, Integer> getMovieList() {
        return movieList;
    }

    public void setMovieList(Map<String, Integer> movieList) {
        this.movieList = movieList;
    }
    
}
