package com.luisfacunat.pokedex.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.luisfacunat.pokedex.R;
import com.luisfacunat.pokedex.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;

    public PokemonAdapter(Context context) {
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Pokemon p = dataset.get(position);
        holder.tv_name.setText(p.getName());

        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .into(holder.iv_picture);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                int pokeid = position + 1;
                Toast.makeText(activity, "clicked pokemon: " + pokeid, Toast.LENGTH_SHORT).show();

                bundle.putInt("id", pokeid);

                Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_PokemonListFragment_to_PokemonSingleFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addtoPokelist(ArrayList<Pokemon> pokemonList) {
        dataset.addAll(pokemonList);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_picture;
        private TextView tv_name;

        ViewHolder(View itemView) {
            super(itemView);

            iv_picture = itemView.findViewById(R.id.pokemon_item_image);
            tv_name = itemView.findViewById(R.id.pokemon_item_name);
        }
    }
}
