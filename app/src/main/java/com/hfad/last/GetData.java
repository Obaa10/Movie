package com.hfad.last;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GetData {
    @GET
    Call<ResultsObject> getAllPhotos(@Url String ip);
}