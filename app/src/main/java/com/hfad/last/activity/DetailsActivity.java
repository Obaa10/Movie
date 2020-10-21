package com.hfad.last.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.last.network.Interface.GetMovieDetailsInterface;
import com.hfad.last.model.MovieDetailsResponse;
import com.hfad.last.R;
import com.hfad.last.network.RetrofitGetDataService;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {


    public static final String MOVIE_ID = "movieId";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Setup the ActionPar
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Integer movieId = (Integer) Objects.requireNonNull(getIntent().getExtras()).get(MOVIE_ID);
        String movieUrl = "/3/movie/" + movieId.toString() + "?api_key=6ddf1da8ede343f82786973e2dd7c457";
        fetchMovieDetails(movieUrl);
    }

    private void fetchMovieDetails (String movieUrl){
        GetMovieDetailsInterface apiService = RetrofitGetDataService.getRetrofitInstance().create(GetMovieDetailsInterface.class);
        Call<MovieDetailsResponse> call = apiService.getAllDetails(movieUrl);
        call.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetailsResponse> call,@NonNull Response<MovieDetailsResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                     MovieDetailsResponse movieDetailsResponse = response.body();
                     attachDataToView(movieDetailsResponse);
                }
            }
            @Override
            public void onFailure(@NonNull Call<MovieDetailsResponse> call,@NonNull Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    private void attachDataToView (MovieDetailsResponse movieDetailsResponse){
        ImageView imageView = findViewById(R.id.backdrop);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieDetailsResponse.getPosterPath()).resize(imageView.getWidth(),0).into(imageView);
        TextView movieVoteAverage = findViewById(R.id.vote_average);
        String voteAverage = getString(R.string.vote_average,movieDetailsResponse.getVoteAverage());
        movieVoteAverage.setText(voteAverage);
        TextView movieOverView = findViewById(R.id.overview);
        String overView = getString(R.string.over_view,movieDetailsResponse.getOverview());
        movieOverView.setText(overView);
        TextView movieLanguage = findViewById(R.id.language);
        String language = getString(R.string.movie_language,movieDetailsResponse.getOriginalLanguage());
        movieLanguage.setText(language);
        TextView isAdult = findViewById(R.id.adult);
        String adult = getString(R.string.is_adult, movieDetailsResponse.getAdult().toString());
        isAdult.setText(adult);
        TextView moviePopulate = findViewById(R.id.Popularity);
        String popularity = getString(R.string.movie_popularity,movieDetailsResponse.getPopularity());
        moviePopulate.setText(popularity);
        TextView movieRuntime =  findViewById(R.id.runtime);
        String runTime = getString(R.string.movie_run_time,movieDetailsResponse.getRuntime());
        movieRuntime.setText(runTime);
        TextView movieTitle = findViewById(R.id.detail_title);
        String title = getString(R.string.movie_title,movieDetailsResponse.getOriginalTitle());
        movieTitle.setText(title);
    }
}