package com.hfad.last.Interface;

import com.hfad.last.Model.MovieDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetMovieDetails {
    @GET
    Call<MovieDetails> getAllDetails(@Url String movieUrl);
}
