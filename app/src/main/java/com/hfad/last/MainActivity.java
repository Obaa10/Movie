package com.hfad.last;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String url = "https://api.themoviedb.org/3/movie/550?api_key=6ddf1da8ede343f82786973e2dd7c457";
    public final String permission = Manifest.permission.INTERNET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MovieAsyncTask movieAsyncTask = new MovieAsyncTask();
        movieAsyncTask.execute(url);
    }




    private class MovieAsyncTask extends AsyncTask<String, Void, Movie> {


        @Override
        protected Movie doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            return Url.fetchMovieData(urls[0]);
        }


        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);

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
