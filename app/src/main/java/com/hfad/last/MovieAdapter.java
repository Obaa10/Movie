package com.hfad.last;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {

    private ArrayList<Movie> movies ;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView ;
        public ViewHolder (CardView v){
            super(v);
            cardView = v;
        }
    }

    public MovieAdapter (ArrayList<Movie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent,false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView name = (TextView) cardView.findViewById(R.id.movie_name);
        TextView genre = (TextView) cardView.findViewById(R.id.movie_vote);
        TextView realised_data = (TextView) cardView.findViewById(R.id.movie_realised_data);
        name.setText(movies.get(position).getName());
        genre.setText(movies.get(position).getVote());
        realised_data.setText(movies.get(position).getData());
        if(position==(movies.size()-5)){
            MainActivity.pos++;
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}
