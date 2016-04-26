/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Sara
 */
public class Movie {
    @SerializedName("id")
    public String id;
    @SerializedName("title")
    private String title;
    @SerializedName("year")
    private String year;
    @SerializedName("mpaa_rating")
    public String mpaa_rating;
    @SerializedName("runtime")
    private String runtime;
    @SerializedName("critics_consensus")
    public String critics_consensus;
    @SerializedName("release_dates")
    public ReleaseDates releaseDates;
    @SerializedName("ratings")
    public Ratings ratings;
    @SerializedName("synopsis")
    private String synopsis;
    @SerializedName("posters")
    private Posters posters;
    @SerializedName("abridged_cast")
    private List<AbridgedCast> abridgedCast;
    @SerializedName("links")
    public Links links;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Posters getPosters() {
        return posters;
    }

    public void setPosters(Posters posters) {
        this.posters = posters;
    }

    public List<AbridgedCast> getAbridgedCast() {
        return abridgedCast;
    }

    public void setAbridgedCast(List<AbridgedCast> abridgedCast) {
        this.abridgedCast = abridgedCast;
    }

    public String toString() {
        String ret = title + " (" + year + ")";
        return ret;
    }
}
