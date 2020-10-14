package com.hfad.last.network.Interface;

import com.hfad.last.model.MoviesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetMovieListInterface {
        @GET
        Call<MoviesListResponse> getAllMovies(@Url String url);}