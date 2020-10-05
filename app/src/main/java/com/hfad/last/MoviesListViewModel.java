package com.hfad.last;

import androidx.lifecycle.ViewModel;

import com.hfad.last.Model.Movie;

import java.util.List;

public class MoviesListViewModel extends ViewModel {
    private List<Movie> finalMoviesList = null;

    public void MovieUpdate(List<Movie> add) {
        finalMoviesList.addAll(add);
    }

    public List<Movie> getFinalMoviesList() {
        return finalMoviesList;
    }

    public void setFinalMoviesList(List<Movie> finalMoviesList) {
        this.finalMoviesList = finalMoviesList;
    }
}
