package com.hfad.last;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GetDetails {
    @GET
    Call<Movie_Details> getAllPhoto(@Url String ip);
}
