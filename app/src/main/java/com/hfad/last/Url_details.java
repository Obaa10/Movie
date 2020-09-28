package com.hfad.last;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Url_details {
/*
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

    private static Movie extractFeatureFromJson(String movieJSON){
        if (TextUtils.isEmpty(movieJSON)){
            return null;
        }
        Movie movie = new Movie();
            try {
                JSONObject jsonMovie = new JSONObject(movieJSON);
                String movie_title = jsonMovie.getString("original_title");
                movie.setMovie_title(movie_title);
                String backdrop_path = jsonMovie.getString("backdrop_path");
               // Bitmap mIcon11 = null;
               // InputStream in = new java.net.URL("https://image.tmdb.org/t/p/original"+backdrop_path).openStream();
              //  mIcon11 = BitmapFactory.decodeStream(in);
              //  movie.setBackdrop_path(mIcon11);
                int vote = jsonMovie.getInt("vote_average");
                movie.setVote(vote);
                String original_language = jsonMovie.getString("original_language");
                movie.setLanguage(original_language);
               // boolean adult = jsonMovie.getBoolean("adult");
                //movie.setAdult(adult);
                String overview = jsonMovie.getString("overview");
                movie.setOverviwe(overview);

            } catch (JSONException e) {
                Log.e("Extractfeaturefromjson", "Problem", e);
            } //catch (MalformedURLException e) {
                //e.printStackTrace();
            //}
            //catch (IOException e) {
            //    e.printStackTrace();
           // }
        return movie;
    }

    public static Movie fetchMovieData(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null ;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extractFeatureFromJson(jsonResponse);
    }

 */

}

