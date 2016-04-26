/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sara
 */
public class OutputRottenTomatoes {
    @SerializedName("total")
    private int total;
    @SerializedName("movies")
    private List<Movie> movies;
   
    @SerializedName("links")
    public APILinks apiLinks;

    @SerializedName("link_template")
    public String linkTemplate;
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    
}
