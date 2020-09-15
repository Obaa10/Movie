package com.hfad.last;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String url = "https://api.themoviedb.org/3/discover/movie?api_key=6ddf1da8ede343f82786973e2dd7c457&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView movieRecyler = (RecyclerView) findViewById(R.id.movie_recycler);
        MovieAdapter movieAdapter = new MovieAdapter(movies);
        movieRecyler.setAdapter(movieAdapter);
        MovieAsyncTask movieAsyncTask = new MovieAsyncTask();
        movieAsyncTask.execute(url);
    }


    private class MovieAsyncTask extends AsyncTask<String, Void,ArrayList<Movie>> {


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
            /*
            TextView name = (TextView) findViewById(R.id.name);
            TextView genre = (TextView) findViewById(R.id.movie_genre);
            TextView realised_data = (TextView) findViewById(R.id.movie_realised_data);
            name.setText(movie.getName());
            genre.setText(movie.getGenre());
            realised_data.setText(movie.getData());
             */

        }
    }
}
