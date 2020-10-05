package com.hfad.last.Interface;

import com.hfad.last.Model.MoviesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetMovieListService {
    @GET
    Call<MoviesList> getAllMovies(@Url String moviesListUrl);
}