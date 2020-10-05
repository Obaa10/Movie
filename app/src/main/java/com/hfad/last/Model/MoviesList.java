package com.hfad.last.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hfad.last.Model.Movie;

import java.util.List;

public class MoviesList {

    @SerializedName("results")
    @Expose
    private List<Movie> results = null;


    public List<Movie> getResults() {
        return results;
    }
}