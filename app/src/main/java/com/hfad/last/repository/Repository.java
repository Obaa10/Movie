package com.hfad.last.repository;

import com.hfad.last.model.MoviesListResponseModel;
import com.hfad.last.network.Interface.GetMovieListInterface;
import com.hfad.last.network.RetrofitGetDataService;
import com.hfad.last.viewmodel.MoviesListViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {


    public Repository() {

    }

    public void getMovieList(Integer ip ) {
        String moviesListUrl = "/3/discover/movie?api_key=6ddf1da8ede343f82786973e2dd7c457&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";
        GetMovieListInterface service = RetrofitGetDataService.getRetrofitInstance().create(GetMovieListInterface.class);
        Call<MoviesListResponseModel> call = service.getAllMovies(moviesListUrl+ip.toString());
        call.enqueue(new Callback<MoviesListResponseModel>() {
            @Override
            public void onResponse(Call<MoviesListResponseModel> call, Response<MoviesListResponseModel> response) {
                MoviesListViewModel.movies.add(0,response.body());
                MoviesListViewModel.allMovieList.setValue(MoviesListViewModel.movies);
            }
            @Override
            public void onFailure(Call<MoviesListResponseModel> call, Throwable t) {
            }
        });
    }
}
