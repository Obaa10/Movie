package com.hfad.last.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.last.Adapter.MovieAdapter;
import com.hfad.last.Interface.GetMovieListRequest;
import com.hfad.last.Model.MovieResponse;
import com.hfad.last.Model.MoviesListResponse;
import com.hfad.last.MoviesListViewModel;
import com.hfad.last.Network.RetrofitGetDataService;
import com.hfad.last.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public final static String CURRENTLY_MOVIE_LIST_PAGE = "currentlyMovieListPage";
    public final static String NEXT_MOVIE_LIST_PAGE = "nextMovieListPage";

    private static String moviesListUrl = "/3/discover/movie?api_key=6ddf1da8ede343f82786973e2dd7c457&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=";
    public static Integer currentlyMovieListPage = 1;
    private static Integer nextMovieListPage = 1;
    private RecyclerView movieRecyclerView;
    private RecyclerView.LayoutManager recyclerViewManager;
    public MovieAdapter recyclerViewAdapter;
    private boolean appIsRunning = false;
    public static MoviesListViewModel moviesListViewModelViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentlyMovieListPage = savedInstanceState.getInt(CURRENTLY_MOVIE_LIST_PAGE);
            nextMovieListPage = savedInstanceState.getInt(NEXT_MOVIE_LIST_PAGE);
        }


        moviesListViewModelViewModel = new ViewModelProvider(this).get(MoviesListViewModel.class);

        //Set the recyclerView
        movieRecyclerView = findViewById(R.id.movie_recycler);
        recyclerViewManager = new LinearLayoutManager(this);
        movieRecyclerView.setLayoutManager(recyclerViewManager);

        //Set the first list
        fetchMovieDetails(moviesListUrl + currentlyMovieListPage.toString());

        updateMovieList();
    }

    //Update the list when the user get the 5 last card
    private void updateMovieList() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             if (currentlyMovieListPage > nextMovieListPage) {
                                 fetchMovieDetails(moviesListUrl + currentlyMovieListPage.toString());
                                 nextMovieListPage += 1;
                             }
                             handler.postDelayed(this, 500);
                         }
                     }
        );
    }


    //Get the movie data from the internet
    private void fetchMovieDetails(String movieUrl) {
        GetMovieListRequest apiService = RetrofitGetDataService.getRetrofitInstance().create(GetMovieListRequest.class);
        Call<MoviesListResponse> call = apiService.getAllMovies(movieUrl);
        call.enqueue(new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<MovieResponse> movieResponseList = response.body().getResults();
                    if (movieResponseList != null) {
                        if (moviesListViewModelViewModel.getFinalMoviesList() == null) {
                            moviesListViewModelViewModel.setFinalMoviesList(movieResponseList);
                        } else {
                            if (currentlyMovieListPage > (moviesListViewModelViewModel.getFinalMoviesList().size() / 20))
                                moviesListViewModelViewModel.MovieUpdate(movieResponseList);
                        }
                        if (!appIsRunning) {
                            recyclerViewAdapter = new MovieAdapter();
                            movieRecyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewManager.getLayoutDirection();
                            appIsRunning = true;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(CURRENTLY_MOVIE_LIST_PAGE, currentlyMovieListPage);
        savedInstanceState.putInt(NEXT_MOVIE_LIST_PAGE, nextMovieListPage);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appIsRunning = false;
    }
}


