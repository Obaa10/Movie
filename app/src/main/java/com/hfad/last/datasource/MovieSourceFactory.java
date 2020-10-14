package com.hfad.last.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.hfad.last.datasource.MovieSource;
import com.hfad.last.model.MovieResponse;

public class MovieSourceFactory extends DataSource.Factory<Integer, MovieResponse> {

    public MutableLiveData<MovieSource> movieSourceMutableLiveData = new MutableLiveData<>();

    @Override
    public DataSource<Integer, MovieResponse> create() {
        MovieSource movieSource = new MovieSource();
        movieSourceMutableLiveData.postValue(movieSource);
        return movieSource;
    }
}
