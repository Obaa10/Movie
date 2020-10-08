package com.hfad.last.network.Interface;

import com.hfad.last.model.MovieDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetMovieDetailsInterface {
    @GET
    Call<MovieDetailsResponse> getAllDetails(@Url String movieUrl);
}
