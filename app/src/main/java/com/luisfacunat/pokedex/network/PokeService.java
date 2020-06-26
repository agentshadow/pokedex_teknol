package com.luisfacunat.pokedex.network;

import com.luisfacunat.pokedex.models.Pokemon;
import com.luisfacunat.pokedex.models.PokemonData;
import com.luisfacunat.pokedex.models.PokemonList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeService {

    @GET("pokemon")
    Call<PokemonList> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{pokemon_id}")
    Call<PokemonData> getPokemonData(@Path("pokemon_id") int id);
}
