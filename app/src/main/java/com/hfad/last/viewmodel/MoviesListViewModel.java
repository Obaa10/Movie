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
    public static Integer currentlyMovieListPage = 1;
    private Repository repository ;
    //private static Integer nextMovieListPage = 1;

    public MoviesListViewModel() {
        allMovieList = new MutableLiveData<>();
        movies = new ArrayList<>();
        repository = new Repository();
        update(1);
    }

    private void update(Integer ip) {
        repository.getMovieList(ip);
    }

    public LiveData<ArrayList<MoviesListResponseModel>> getAllMovieList() {
        return allMovieList;
    }
}
