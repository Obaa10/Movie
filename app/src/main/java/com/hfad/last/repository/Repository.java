package com.hfad.last.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hfad.last.model.MoviesListResponse;
import com.hfad.last.network.Interface.GetMovieListInterface;
import com.hfad.last.network.RetrofitGetDataService;
import com.hfad.last.viewmodel.MoviesListViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static String moviesListUrl = "/3/discover/movie?api_key=6ddf1da8ede343f82786973e2dd7c457&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";

    private GetMovieListInterface mApiService;

    public Repository() {
        mApiService = RetrofitGetDataService.getRetrofitInstance().create(GetMovieListInterface.class);

    }

    public LiveData<MoviesListResponse> getMovieList() {

        final MutableLiveData<MoviesListResponse> mMovieList = new MutableLiveData<>();

        mApiService.getAllMovies(moviesListUrl+ MoviesListViewModel.currentlyMovieListPage.toString()).enqueue(new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {
                mMovieList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
        return mMovieList;
    }
}
