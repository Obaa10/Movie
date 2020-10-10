package com.hfad.last.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.last.R;
import com.hfad.last.adapter.MovieAdapter;
import com.hfad.last.model.MoviesListResponseModel;
import com.hfad.last.viewmodel.MoviesListViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    public MovieAdapter recyclerViewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Set the recyclerView
        RecyclerView movieRecyclerView = findViewById(R.id.movie_recycler);
        RecyclerView.LayoutManager recyclerViewManager = new LinearLayoutManager(this);
        movieRecyclerView.setLayoutManager(recyclerViewManager);
        recyclerViewAdapter = new MovieAdapter();
        movieRecyclerView.setAdapter(recyclerViewAdapter);

        final MoviesListViewModel moviesListViewModel = new ViewModelProvider(this).get(MoviesListViewModel.class);

        observeViewModel(moviesListViewModel);
    }


    private void observeViewModel(MoviesListViewModel moviesListViewModel) {
        moviesListViewModel.getAllMovieList().observe(this, new Observer<ArrayList<MoviesListResponseModel>>() {
            @Override
            public void onChanged(ArrayList<MoviesListResponseModel> moviesListResponseModels) {
                    recyclerViewAdapter.addAll(moviesListResponseModels.get(0).getResults());
            }
        });
    }
}


// Toast.makeText(getApplicationContext(),"The text you want to display",Toast.LENGTH_LONG).show();

//Update the list when the user get the 5 last card
   /* private void updateMovieList() {
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

    */

//Get the movie data from the internet
   /* private void fetchMovieDetails(String movieUrl) {
        GetMovieListInterface apiService = RetrofitGetDataService.getRetrofitInstance().create(GetMovieListInterface.class);
        Call<MoviesListResponseModel> call = apiService.getAllMovies(movieUrl);
        call.enqueue(new Callback<MoviesListResponseModel>() {
            @Override
            public void onResponse(Call<MoviesListResponseModel> call, Response<MoviesListResponseModel> response) {
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
            public void onFailure(Call<MoviesListResponseModel> call, Throwable t) {
                Log.d("TAG", "Response = " + t.toString());
            }
        });
    }
    */


