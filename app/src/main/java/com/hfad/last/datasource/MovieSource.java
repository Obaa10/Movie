package com.hfad.last.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.hfad.last.model.MovieResponse;
import com.hfad.last.model.MoviesListResponse;
import com.hfad.last.network.Interface.GetMovieListInterface;
import com.hfad.last.network.RetrofitGetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSource extends PageKeyedDataSource<Integer, MovieResponse> {


    private static final Integer FIRST_PAGE = 1;
    private static final String MOVIE_URL = "/3/discover/movie?api_key=6ddf1da8ede343f82786973e2dd7c457&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";
    public static final Integer LIST_SIZE = 20;

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, MovieResponse> callback) {
        GetMovieListInterface service = RetrofitGetDataService.getRetrofitInstance().create(GetMovieListInterface.class);
        Call<MoviesListResponse> call = service.getAllMovies(MOVIE_URL + FIRST_PAGE.toString());
        call.enqueue(new Callback<MoviesListResponse>() {

            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {
                if (response.body() != null) {
                    List<MovieResponse> movies = response.body().getResults();
                    callback.onResult(movies, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieResponse> callback) {
        GetMovieListInterface service = RetrofitGetDataService.getRetrofitInstance().create(GetMovieListInterface.class);
        Call<MoviesListResponse> call = service.getAllMovies(MOVIE_URL + params.key.toString());
        call.enqueue(new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {
                if (response.body() != null) {
                    List<MovieResponse> movies = response.body().getResults();
                    Integer key;
                    if (params.key > 1)
                        key = params.key - 1;
                    else
                        key = 0;
                    callback.onResult(movies,key);
                }
            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieResponse> callback) {
        GetMovieListInterface service = RetrofitGetDataService.getRetrofitInstance().create(GetMovieListInterface.class);
        Call<MoviesListResponse> call = service.getAllMovies(MOVIE_URL + params.key.toString());
        call.enqueue(new Callback<MoviesListResponse>() {

            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {
                if (response.body() != null) {
                    List<MovieResponse> movies = response.body().getResults();
                    callback.onResult(movies,params.key+1);
                }
            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {

            }
        });
    }
}
