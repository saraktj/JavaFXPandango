/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *
 * @author Sara
 */
class AbridgedCast {

    @SerializedName("name")
    public String name;

    @SerializedName("characters")
    public List<String> characters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }
}
