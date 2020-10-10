package com.hfad.last.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.last.R;
import com.hfad.last.activity.DetailsActivity;
import com.hfad.last.model.MovieResponse;
import com.hfad.last.viewmodel.MoviesListViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public final static String MOVIE_ID = "movieId";


    private List<MovieResponse> movieResponses;


    public MovieAdapter() {
        movieResponses = new ArrayList<>();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView movieCardViewM;

        ViewHolder(CardView cardView) {
            super(cardView);
            movieCardViewM = cardView;
        }
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int movieCardPosition) {
        CardView cardView = holder.movieCardViewM;
        TextView movieName = cardView.findViewById(R.id.movie_name);
        TextView movieRealisedData = cardView.findViewById(R.id.movie_realised_data);
        TextView movieVoteAverage = cardView.findViewById(R.id.movie_vote);
        movieName.setText(movieResponses.get(movieCardPosition).getTitle());
        movieRealisedData.setText(movieResponses.get(movieCardPosition).getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w185" + movieResponses.get(movieCardPosition).getPosterPath()).into(holder.movieCardViewM.<ImageView>findViewById(R.id.movie_image));
        // movieVoteAverage.setText(movieResponses.get(movieCardPosition).getVoteAverage().toString());
        if (movieCardPosition >= (MoviesListViewModel.currentlyMovieListPage * 20) - 5) {
            MoviesListViewModel.currentlyMovieListPage++;
            MoviesListViewModel.currentlyMovieListPageM.setValue(MoviesListViewModel.currentlyMovieListPage);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra(MOVIE_ID, movieResponses.get(movieCardPosition).getId());
                v.getContext().startActivity(intent);
            }

        });
    }

    public void addAll(List<MovieResponse> list) {
        movieResponses.addAll(list);
        notifyDataSetChanged();
    }

    public void create() {
        movieResponses.clear();
        for (int i = 0; i < MoviesListViewModel.currentlyMovieListPage; i++)
            movieResponses.addAll(MoviesListViewModel.movies.get(i).getResults());
    }

    @Override
    public int getItemCount() {
        return movieResponses.size();
    }

}
