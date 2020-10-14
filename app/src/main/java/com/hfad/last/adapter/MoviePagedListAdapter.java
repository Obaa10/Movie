package com.hfad.last.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.last.R;
import com.hfad.last.activity.DetailsActivity;
import com.hfad.last.model.MovieResponse;
import com.squareup.picasso.Picasso;


public class MoviePagedListAdapter extends PagedListAdapter<MovieResponse, MoviePagedListAdapter.ViewHolder> {


    public MoviePagedListAdapter() {
        super(USER_COMPARATOR);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MovieResponse movie = getItem(position);
        if (movie != null) {
            CardView cardView = holder.movieCardViewM;
            TextView movieName = cardView.findViewById(R.id.movie_name);
            TextView movieRealisedData = cardView.findViewById(R.id.movie_realised_data);
            TextView movieVoteAverage = cardView.findViewById(R.id.movie_vote);
            movieName.setText(movie.getTitle());
            movieRealisedData.setText(movie.getReleaseDate());
            Picasso.get().load("https://image.tmdb.org/t/p/w185" + movie.getPosterPath()).into(holder.movieCardViewM.<ImageView>findViewById(R.id.movie_image));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                    intent.putExtra(DetailsActivity.MOVIE_ID, movie.getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView movieCardViewM;

        ViewHolder(CardView cardView) {
            super(cardView);
            movieCardViewM = cardView;
        }
    }

    private static final DiffUtil.ItemCallback<MovieResponse> USER_COMPARATOR = new DiffUtil.ItemCallback<MovieResponse>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieResponse oldItem, @NonNull MovieResponse newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieResponse oldItem, @NonNull MovieResponse newItem) {
            return oldItem.getAdult().equals(newItem.getAdult());
        }
    };
}

