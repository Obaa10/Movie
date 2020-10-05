package com.hfad.last.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.last.Adapter.MovieAdapter;
import com.hfad.last.Interface.GetMovieDetails;
import com.hfad.last.Model.MovieDetails;
import com.hfad.last.R;
import com.hfad.last.Network.RetrofitGetDataService;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Setup the ActionPar
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Integer movieId = (Integer) Objects.requireNonNull(getIntent().getExtras()).get(MovieAdapter.MOVIE_ID);
        String movieUrl = "/3/movie/" + movieId.toString() + "?api_key=6ddf1da8ede343f82786973e2dd7c457";
        fetchMovieDetails(movieUrl);
    }

    private void fetchMovieDetails (String movieUrl){
        GetMovieDetails apiService = RetrofitGetDataService.getRetrofitInstance().create(GetMovieDetails.class);
        Call<MovieDetails> call = apiService.getAllDetails(movieUrl);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                     MovieDetails movieDetails = response.body();
                     attachDataToView(movieDetails);
                }
            }
            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    private void attachDataToView (MovieDetails movieDetails){
        ImageView imageView = findViewById(R.id.backdrop);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieDetails.getPosterPath()).resize(imageView.getWidth(),0).into(imageView);
        TextView movieVoteAverage = findViewById(R.id.vote_average);
        movieVoteAverage.setText(movieDetails.getVoteAverage().toString());
        TextView movieOverView = findViewById(R.id.overview);
        movieOverView.setText("OverView"+"\n"+movieDetails.getOverview());
        TextView movieLanguage = findViewById(R.id.language);
        movieLanguage.setText(movieDetails.getOriginalLanguage());
        TextView isAdult = findViewById(R.id.adult);
        isAdult.setText("Adult"+"\n"+movieDetails.getAdult().toString());
        TextView moviePopulate = findViewById(R.id.Popularity);
        moviePopulate.setText(movieDetails.getPopularity().toString());
        TextView movieRuntime =  findViewById(R.id.runtime);
        movieRuntime.setText(movieDetails.getRuntime().toString());
        TextView movieTitle = findViewById(R.id.detail_title);
        movieTitle.setText(movieDetails.getOriginalTitle());
    }
}