package com.hfad.last;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    private static String url = "/3/discover/movie?api_key=6ddf1da8ede343f82786973e2dd7c457&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";
    public static Integer pos=1;
    private RecyclerView movieRecycler;
    private RecyclerView.LayoutManager layoutManager ;
    public MovieAdapter movieAdapter ;
    private static Integer pose = 1;
    private boolean running = false;
    public static MyMovie myMovie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null) {
            pos = savedInstanceState.getInt("pos");
            pose = savedInstanceState.getInt("pose");
        }
        myMovie = ViewModelProviders.of(this).get(MyMovie.class);

        //Set the recyclerView
        movieRecycler = (RecyclerView) findViewById(R.id.movie_recycler);
        layoutManager = new LinearLayoutManager(this);
        movieRecycler.setLayoutManager(layoutManager);

        //Set the first list
        Do(url+pos.toString());

        //Run the the update method
        update();
    }

    //Update the list when the user get the 5 last card
    private void update (){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(pos>pose){
                    Do(url+pos.toString());
                    pose+=1;
                }
                handler.postDelayed(this,500);
            }
        }
        );
    }

    //Get the movie data from the internet
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
                        if (myMovie.getMyList() == null) {
                            myMovie.setMyList(moveList);
                        } else {if(pos>(myMovie.getMyList().size()/20))
                            myMovie.MovieUpdate(moveList);
                        }
                        if (!running) {
                            movieAdapter = new MovieAdapter();
                            movieRecycler.setAdapter(movieAdapter);
                            layoutManager.getLayoutDirection();
                            running = true;
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("pos",pos);
        savedInstanceState.putInt("pose",pose);
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }
}


