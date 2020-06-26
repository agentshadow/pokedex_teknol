package com.luisfacunat.pokedex.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonList {

    @SerializedName("results")
    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
