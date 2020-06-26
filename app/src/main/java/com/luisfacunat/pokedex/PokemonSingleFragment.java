package com.luisfacunat.pokedex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.luisfacunat.pokedex.adapters.PokemonDataAdapter;
import com.luisfacunat.pokedex.models.PokemonData;
import com.luisfacunat.pokedex.network.PokeService;
import com.luisfacunat.pokedex.network.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonSingleFragment extends Fragment {

    private PokemonDataAdapter pokemonDataAdapter;
    int pokemon_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        pokemon_id = getArguments().getInt("id");
        getSinglePokemonData(pokemon_id);
        //Toast.makeText(getContext(), "Pokémon" + pokemon_id, Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.fragment_pokemon_single, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        pokemon_id = getArguments().getInt("id");

        //Toast.makeText(getContext(), "onViewCreated " + pokemon_id, Toast.LENGTH_SHORT).show();
    }

    private void getSinglePokemonData(int id) {

        PokeService service = RetrofitClientInstance.getRetrofitInstance().create(PokeService.class);

        Call<PokemonData> pokemonDataCall = service.getPokemonData(id);

        pokemonDataCall.enqueue(new Callback<PokemonData>() {

            @Override
            public void onResponse(Call<PokemonData> call, Response<PokemonData> response) {

                if(response.isSuccessful()) {

                    response.body();
                    String id = response.body().getId();
                    String name = response.body().getName();
                    String experience = response.body().getBase_experience();
                    String height = response.body().getHeight();
                    String weight = response.body().getWeight();

                    TextView tv_name, tv_id, tv_exp, tv_weight, tv_height;
                    ImageView sprite;

                    sprite = getView().findViewById(R.id.pokemon_single_item_sprite);
                    tv_name = getView().findViewById(R.id.pokemon_single_item_name);
                    tv_id = getView().findViewById(R.id.pokemon_single_number);
                    tv_exp = getView().findViewById(R.id.pokemon_single_base_experience);
                    tv_height = getView().findViewById(R.id.pokemon_single_item_height);
                    tv_weight = getView().findViewById(R.id.pokemon_single_item_weight);
                    tv_name.setText(name);
                    tv_id.setText(id);
                    tv_exp.setText(experience);
                    tv_height.setText(height);
                    tv_weight.setText(weight);

                    Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png")
                            .into(sprite);

                    Toast.makeText(getContext(), "Data OK", Toast.LENGTH_SHORT).show();
                } else {

                    //Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonData> call, Throwable t) {

                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
