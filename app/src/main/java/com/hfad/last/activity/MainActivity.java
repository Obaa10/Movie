package com.hfad.last.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.last.R;
import com.hfad.last.adapter.MoviePagedListAdapter;
import com.hfad.last.model.MovieResponse;
import com.hfad.last.viewmodel.MovieListViewModel;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movie_recycler);
        final MoviePagedListAdapter movieAdapter = new MoviePagedListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieListViewModel movieViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        movieViewModel.pagedListLiveData.observe(this, new Observer<PagedList<MovieResponse>>() {
            @Override
            public void onChanged(PagedList<MovieResponse> movies) {
                movieAdapter.submitList(movies);
            }
        });
        recyclerView.setAdapter(movieAdapter);
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
                            recyclerViewAdapter = new MoviePagedListAdapter();
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
    */


