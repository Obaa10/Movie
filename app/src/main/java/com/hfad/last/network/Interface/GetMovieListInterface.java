package com.hfad.last.network.Interface;

import com.hfad.last.model.MoviesListResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetMovieListInterface {
    @GET
    Call<MoviesListResponseModel> getUsers(@Url String url);}