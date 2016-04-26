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
class Links {
    @SerializedName("self")
	public String self;

	@SerializedName("alternate")
	public String alternate;

	@SerializedName("cast")
	public String cast;

	@SerializedName("clips")
	public String clips;

	@SerializedName("review")
	public String review;

	@SerializedName("similar")
	public String similar;
}
