package com.hfad.last.Adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.last.Activity.DetailsActivity;
import com.hfad.last.Activity.MainActivity;
import com.hfad.last.R;
import com.squareup.picasso.Picasso;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public final static String MOVIE_ID = "movieId";


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
        movieName.setText(MainActivity.moviesListViewModelViewModel.getFinalMoviesList().get(movieCardPosition).getTitle());
        movieRealisedData.setText(MainActivity.moviesListViewModelViewModel.getFinalMoviesList().get(movieCardPosition).getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w185" + MainActivity.moviesListViewModelViewModel.getFinalMoviesList().get(movieCardPosition).getPosterPath()).into(holder.movieCardViewM.<ImageView>findViewById(R.id.movie_image));
        movieVoteAverage.setText(MainActivity.moviesListViewModelViewModel.getFinalMoviesList().get(movieCardPosition).getVoteAverage().toString());
        if (movieCardPosition >= (MainActivity.currentlyMovieListPage * 10 * 2 - 5)) {
            MainActivity.currentlyMovieListPage += 1;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra(MOVIE_ID, MainActivity.moviesListViewModelViewModel.getFinalMoviesList().get(movieCardPosition).getId());
                v.getContext().startActivity(intent);
            }

        });


    }

    @Override
    public int getItemCount() {
        return MainActivity.moviesListViewModelViewModel.getFinalMoviesList().size();
    }

}
