package com.hfad.last;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class details extends AppCompatActivity {
    private Integer id;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        id = (Integer) getIntent().getExtras().get("id");
        url = "https://api.themoviedb.org/3/movie/" + id.toString() + "?api_key=6ddf1da8ede343f82786973e2dd7c457";
        //    details.MovieAsyncTask movieAsyncTask = new details.MovieAsyncTask();
        //    movieAsyncTask.execute(url);
    }
}

   // private class MovieAsyncTask extends AsyncTask<String,Void,Movie>{


    /*    @Override
        protected Movie doInBackground(String... urls) {
            return Url_details.fetchMovieData(urls[0]);
        }


     */
      /*  protected void onPostExecute(Movie movies) {
            super.onPostExecute(movies);
           // ImageView imageView = (ImageView) findViewById(R.id.backdrop);
           // imageView.setImageBitmap(movies.getBackdrop_path());
            TextView title = (TextView) findViewById(R.id.movie_title);
            title.setText(movies.getMovie_title());
            //TextView vote = (TextView) findViewById(R.id.vote_average);
            //vote.setText(movies.getVote());
            TextView overview = (TextView) findViewById(R.id.overview);
            overview.setText(movies.getOverviwe());
            TextView language = (TextView) findViewById(R.id.language);
            language.setText(movies.getLanguage());
            }
        }

       */

