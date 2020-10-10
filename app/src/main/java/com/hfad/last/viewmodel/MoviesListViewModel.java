package com.hfad.last.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hfad.last.model.MoviesListResponseModel;
import com.hfad.last.repository.Repository;

import java.util.ArrayList;


public class MoviesListViewModel extends ViewModel {
    public static MutableLiveData<ArrayList<MoviesListResponseModel>> allMovieList;
    public static ArrayList<MoviesListResponseModel> movies;
    public static Integer currentlyMovieListPage = 0;
    public static MutableLiveData<Integer> currentlyMovieListPageM;
    private Repository repository;

    public MoviesListViewModel() {
        allMovieList = new MutableLiveData<>();
        currentlyMovieListPageM = new MutableLiveData<>();
        movies = new ArrayList<>();
        repository = new Repository();
        update(0);
    }

    public void update(Integer ip) {
        repository.getMovieList(ip + 1);
    }

    public LiveData<ArrayList<MoviesListResponseModel>> getAllMovieList() {
        return allMovieList;
    }

    public LiveData<Integer> getCurrentlyMovieListPage() {
        return currentlyMovieListPageM;
    }
}
