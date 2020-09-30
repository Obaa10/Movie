package com.hfad.last;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.shape.RoundedCornerTreatment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details_Activity extends AppCompatActivity {
    private Integer id;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Setup the ActionPar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle((String) getIntent().getExtras().get("name"));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        id = (Integer) getIntent().getExtras().get("id");
        url = "/3/movie/" + id.toString() + "?api_key=6ddf1da8ede343f82786973e2dd7c457";
        Do(url);
    }

    private void Do (String id){
        GetDetails apiService = RetrofitGetData.getRetrofitInstance().create(GetDetails.class);
        Call<Movie_Details> call = apiService.getAllPhoto(id);
        call.enqueue(new Callback<Movie_Details>() {
            @Override
            public void onResponse(Call<Movie_Details> call, Response<Movie_Details> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                     Movie_Details movie_details = response.body();
                     set(movie_details);
                }
            }
            @Override
            public void onFailure(Call<Movie_Details> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    private void set (Movie_Details movieDetails){
        //TextView title = (TextView) findViewById(R.id.movie_title);
        //title.setText(movieDetails.getOriginalTitle());
        ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movieDetails.getPosterPath()).resize(imageView.getWidth(),0).into(imageView);
        TextView vote = (TextView) findViewById(R.id.vote_average);
        vote.setText(movieDetails.getVoteAverage().toString());
        TextView overView = (TextView) findViewById(R.id.overview);
        overView.setText("OverView"+"\n"+movieDetails.getOverview());
        TextView language = (TextView) findViewById(R.id.language);
        language.setText(movieDetails.getOriginalLanguage());
        TextView adult = (TextView) findViewById(R.id.adult);
        adult.setText("Adult"+"\n"+movieDetails.getAdult().toString());
        TextView populate = (TextView) findViewById(R.id.Popularity);
        populate.setText(movieDetails.getPopularity().toString());
        TextView runtime = (TextView) findViewById(R.id.runtime);
        runtime.setText(movieDetails.getRuntime().toString());
    }
}