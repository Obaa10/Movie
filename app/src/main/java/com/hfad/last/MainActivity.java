package com.hfad.last;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static  String url = "/3/discover/movie?api_key=6ddf1da8ede343f82786973e2dd7c457&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";
    private List<Movie> movies = new ArrayList<Movie>();
    public static int pos=1;
    private RecyclerView movieRecyler;
    private RecyclerView.LayoutManager layoutManager ;
    public MovieAdapter movieAdapter ;
    private static Integer pose = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        // get data from the internet


        // set the recyclerView
        movieRecyler = (RecyclerView) findViewById(R.id.movie_recycler);
        layoutManager = new LinearLayoutManager(this);
        movieRecyler.setLayoutManager(layoutManager);

        // set the adapter
        Do(url+"1");
        update();

    }




    private void update (){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(pos>pose){
                    pose+=1;
                    Do(url+pose.toString());
                }
                handler.postDelayed(this,1000);
            }
        }
        );
    }




    private void Do (String id){
        GetData apiService = RetrofitGetData.getRetrofitInstance().create(GetData.class);
        Call<ResultsObject> call = apiService.getAllPhotos(id);
        call.enqueue(new Callback<ResultsObject>() {
            @Override
            public void onResponse(Call<ResultsObject> call, Response<ResultsObject> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<Movie> moveList = response.body().getResults();
                    if (moveList != null) {
                        if (pos == 1) {
                            movieAdapter = new MovieAdapter(moveList);
                            movieRecyler.setAdapter(movieAdapter);
                            layoutManager.getLayoutDirection();
                        } else {
                            movieAdapter.update(moveList);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<ResultsObject> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }
    }


