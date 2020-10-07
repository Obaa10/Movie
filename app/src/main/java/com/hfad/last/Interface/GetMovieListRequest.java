package com.hfad.last.Interface;

import com.hfad.last.Model.MoviesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetMovieListRequest {
    @GET
    Call<MoviesListResponse> getAllMovies(@Url String moviesListUrl);
}