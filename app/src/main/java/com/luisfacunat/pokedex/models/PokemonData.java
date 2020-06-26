package com.luisfacunat.pokedex.models;

import com.google.gson.annotations.SerializedName;

public class PokemonData {

    @SerializedName("base_experience")
    private String base_experience;
    @SerializedName("height")
    private String height;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("weight")
    private String weight;

    public String getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(String base_experience) {
        this.base_experience = base_experience;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
