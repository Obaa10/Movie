package com.hfad.last.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hfad.last.model.MoviesListResponse;
import com.hfad.last.repository.Repository;


public class MoviesListViewModel extends ViewModel {
    private LiveData<MoviesListResponse> allMovieList;
    public static Integer currentlyMovieListPage = 1;
    private static Integer nextMovieListPage = 1;
    private Repository repository;
    public MoviesListViewModel (){
        repository = new Repository();
        allMovieList = repository.getMovieList();
    }


    public LiveData<MoviesListResponse> getAllMovieList() {
        return allMovieList;
    }
}
