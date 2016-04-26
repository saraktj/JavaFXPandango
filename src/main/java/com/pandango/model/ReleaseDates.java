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
class ReleaseDates {
    @SerializedName("theater")
	public String theater;
	
	@SerializedName("dvd")
	public String dvd;
}
