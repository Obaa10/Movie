package com.hfad.last.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hfad.last.model.MoviesListResponseModel;
import com.hfad.last.repository.Repository;

import java.util.ArrayList;



public class MoviesListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<MoviesListResponseModel>> allMovieList;
    public static Integer currentlyMovieListPage = 1;
    //private static Integer nextMovieListPage = 1;

    public MoviesListViewModel (){
        allMovieList = new MutableLiveData<>();
        Repository repository = new Repository();
        ArrayList<MoviesListResponseModel> movies = new ArrayList<>();
        if(repository.getMovieList()!=null)
            movies.add(repository.getMovieList());
        allMovieList.setValue(movies);
    }

    public LiveData<ArrayList<MoviesListResponseModel>> getAllMovieList() {
        return allMovieList;
    }
}
