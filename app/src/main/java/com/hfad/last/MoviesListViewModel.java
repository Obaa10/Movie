package com.hfad.last;

import androidx.lifecycle.ViewModel;

import com.hfad.last.Model.MovieResponse;

import java.util.List;

public class MoviesListViewModel extends ViewModel {
    private List<MovieResponse> finalMoviesList = null;

    public void MovieUpdate(List<MovieResponse> add) {
        finalMoviesList.addAll(add);
    }

    public List<MovieResponse> getFinalMoviesList() {
        return finalMoviesList;
    }

    public void setFinalMoviesList(List<MovieResponse> finalMoviesList) {
        this.finalMoviesList = finalMoviesList;
    }
}
