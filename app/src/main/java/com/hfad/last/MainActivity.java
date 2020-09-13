package com.hfad.last;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.InetAddress;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String url = "https://api.themoviedb.org/3/movie/550?api_key=6ddf1da8ede343f82786973e2dd7c457";
    public final String permission = Manifest.permission.INTERNET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check the internet access
        // print true if the app access the internet
        TextView textView = (TextView) findViewById(R.id.name);
        if(isInternetAvailable())
            textView.setText("True");


        MovieAsyncTask movieAsyncTask = new MovieAsyncTask();
        movieAsyncTask.execute(url);
    }


    // check the internet access
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
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
        protected void onPostExecute(Movie data) {
            TextView textView = (TextView) findViewById(R.id.name);
            textView.setText("hi there ");
            textView.setText(data.getName());


        }
    }
}
