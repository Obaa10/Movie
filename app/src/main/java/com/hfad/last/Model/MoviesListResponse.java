package com.hfad.last.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesListResponse {

    @SerializedName("results")
    @Expose
    private List<MovieResponse> results = null;


    public List<MovieResponse> getResults() {
        return results;
    }
}