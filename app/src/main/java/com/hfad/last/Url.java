package com.hfad.last;
import android.text.TextUtils;
import android.util.Log;

import androidx.constraintlayout.utils.widget.MockView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Url  {
    private static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("URl create", "Problem with create url");
        }
        return url;
    }

    private static  String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url==null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Log.e("Http request", "Not 200");
            }
        } catch (IOException e) {
            Log.e("Http request", "Not request", e);
        }
        finally {
            if (urlConnection !=null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream)throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static ArrayList<Movie> extractFeatureFromJson(String movieJSON){
        if (TextUtils.isEmpty(movieJSON)){
            return null;
        }
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0;i<20;i++) {
            try {
                Movie movie = new Movie();
                JSONObject jsonObject = new JSONObject(movieJSON);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                JSONObject jsonMovie = jsonArray.getJSONObject(i);
                String title = jsonMovie.getString("title");
                movie.setName(title);

                //int vote = jsonMovie.getInt("vote_average");
                //movie.setVote(vote);

                String realised_data = jsonMovie.getString("release_date");
                movie.setData(realised_data);

                movies.add(movie);
            } catch (JSONException e) {
                Log.e("Extractfeaturefromjson", "Problem", e);
            }
        }
        return movies;
    }


    public static ArrayList<Movie> fetchMovieData(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null ;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extractFeatureFromJson(jsonResponse);
    }

}
