package com.hfad.last;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CalendarView;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static  String url = "https://api.themoviedb.org/3/discover/movie?api_key=6ddf1da8ede343f82786973e2dd7c457&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    public static int pos=1;
    private MovieAdapter movieAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the data from the internet
        MovieAsyncTask movieAsyncTask = new MovieAsyncTask();
        movieAsyncTask.execute(url+pos);

        RecyclerView movieRecyler = (RecyclerView) findViewById(R.id.movie_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        movieRecyler.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(movies);
        movieRecyler.setAdapter(movieAdapter);
        layoutManager.getLayoutDirection();
        movieAdapter.getItemCount();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int pose = 1;
        if(pos> pose){
            pose += 1;
            url = url+pose;
            MovieAsyncTask movieAsyncTask = new MovieAsyncTask();
            movieAsyncTask.execute(url+pose);
            movieAdapter = new MovieAdapter(movies);
        }
    }

    private class MovieAsyncTask extends AsyncTask<String, Void,ArrayList<Movie>> {

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Movie> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            return Url.fetchMovieData(urls[0]);
        }


        @Override
        protected void onPostExecute(ArrayList<Movie> moviess) {
            super.onPostExecute(moviess);
            movies = moviess;
        }
    }
}
