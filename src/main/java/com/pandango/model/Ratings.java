/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Sara
 */
class Ratings {
    @SerializedName("critics_rating")
	public String criticsRating;

	@SerializedName("critics_score")
	public int criticsScore;

	@SerializedName("audience_rating")
	public String audienceRating;

	@SerializedName("audience_score")
	public int audienceScore;
}
