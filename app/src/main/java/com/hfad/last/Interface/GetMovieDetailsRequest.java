package com.hfad.last.Interface;

import com.hfad.last.Model.MovieDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetMovieDetailsRequest {
    @GET
    Call<MovieDetailsResponse> getAllDetails(@Url String movieUrl);
}
