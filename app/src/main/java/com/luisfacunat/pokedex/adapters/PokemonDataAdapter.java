package com.luisfacunat.pokedex.adapters;

import android.content.Context;
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
import com.luisfacunat.pokedex.models.PokemonData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PokemonDataAdapter extends RecyclerView.Adapter<PokemonDataAdapter.CustomViewHolder> {

    private ArrayList<PokemonData> dataset;

    public PokemonDataAdapter(Context context, ArrayList<PokemonData> dataset) {
        this.dataset = dataset;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_single_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        PokemonData p = dataset.get(position);
        holder.tv_name.setText(p.getName());

        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getId() + ".png")
                .into(holder.iv_picture);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }



    static class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_picture;
        private TextView tv_name;

        CustomViewHolder(View itemView) {
            super(itemView);

            iv_picture = itemView.findViewById(R.id.pokemon_single_item_image);
            tv_name = itemView.findViewById(R.id.pokemon_single_item_name);
        }
    }
}
