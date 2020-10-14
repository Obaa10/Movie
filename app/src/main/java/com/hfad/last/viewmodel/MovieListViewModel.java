package com.hfad.last.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.hfad.last.datasource.MovieSource;
import com.hfad.last.datasource.MovieSourceFactory;
import com.hfad.last.model.MovieResponse;


public class MovieListViewModel extends ViewModel {

    public LiveData<PagedList<MovieResponse>> pagedListLiveData;

    private LiveData<MovieSource> movieSourceLiveData;

    public MovieListViewModel() {
        MovieSourceFactory factory = new MovieSourceFactory();
        movieSourceLiveData = factory.movieSourceMutableLiveData;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(MovieSource.LIST_SIZE)
                .build();
        pagedListLiveData = new LivePagedListBuilder<>(factory,config).build();
    }
}
