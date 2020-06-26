package com.luisfacunat.pokedex;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luisfacunat.pokedex.adapters.PokemonAdapter;
import com.luisfacunat.pokedex.models.Pokemon;
import com.luisfacunat.pokedex.models.PokemonList;
import com.luisfacunat.pokedex.network.PokeService;
import com.luisfacunat.pokedex.network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListFragment extends Fragment {

    private static final String TAG = "POKEDEX";
    private PokemonAdapter pokemonAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int offset = 0;
        getPokemonData(offset);

        return inflater.inflate(R.layout.fragment_pokemon_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void getPokemonData(int offset) {

        PokeService service = RetrofitClientInstance.getRetrofitInstance().create(PokeService.class);

        Call<PokemonList> PokemonListCall = service.getPokemonList(20, offset);

        PokemonListCall.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {

                RecyclerView recyclerView = getView().findViewById(R.id.pokemonRecycler);
                pokemonAdapter = new PokemonAdapter(getContext());
                recyclerView.setAdapter(pokemonAdapter);
                recyclerView.setHasFixedSize(true);
                final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
                recyclerView.setLayoutManager(layoutManager);

                if (response.isSuccessful()) {

                    PokemonList pokelist = response.body();
                    ArrayList<Pokemon> pokemonList = pokelist.getResults();

                    pokemonAdapter.addtoPokelist(pokemonList);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }
}
