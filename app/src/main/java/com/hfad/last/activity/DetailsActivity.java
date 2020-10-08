package com.hfad.last.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfad.last.adapter.MovieAdapter;
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
        GetMovieDetailsInterface apiService = RetrofitGetDataService.getRetrofitInstance().create(GetMovieDetailsInterface.class);
        Call<MovieDetailsResponse> call = apiService.getAllDetails(movieUrl);
        call.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(Call<MovieDetailsResponse> call, Response<MovieDetailsResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                     MovieDetailsResponse movieDetailsResponse = response.body();
                     attachDataToView(movieDetailsResponse);
                }
            }
            @Override
            public void onFailure(Call<MovieDetailsResponse> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    private void attachDataToView (MovieDetailsResponse movieDetailsResponse){
        ImageView imageView = findViewById(R.id.backdrop);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieDetailsResponse.getPosterPath()).resize(imageView.getWidth(),0).into(imageView);
        TextView movieVoteAverage = findViewById(R.id.vote_average);
        movieVoteAverage.setText(movieDetailsResponse.getVoteAverage().toString());
        TextView movieOverView = findViewById(R.id.overview);
        movieOverView.setText("OverView"+"\n"+ movieDetailsResponse.getOverview());
        TextView movieLanguage = findViewById(R.id.language);
        movieLanguage.setText(movieDetailsResponse.getOriginalLanguage());
        TextView isAdult = findViewById(R.id.adult);
        isAdult.setText("Adult"+"\n"+ movieDetailsResponse.getAdult().toString());
        TextView moviePopulate = findViewById(R.id.Popularity);
        moviePopulate.setText(movieDetailsResponse.getPopularity().toString());
        TextView movieRuntime =  findViewById(R.id.runtime);
        movieRuntime.setText(movieDetailsResponse.getRuntime().toString());
        TextView movieTitle = findViewById(R.id.detail_title);
        movieTitle.setText(movieDetailsResponse.getOriginalTitle());
    }
}