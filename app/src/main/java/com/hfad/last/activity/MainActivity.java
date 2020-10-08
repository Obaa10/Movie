package com.hfad.last.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.last.adapter.MovieAdapter;
import com.hfad.last.network.Interface.GetMovieListInterface;
import com.hfad.last.model.MovieResponse;
import com.hfad.last.model.MoviesListResponse;
import com.hfad.last.viewmodel.MoviesListViewModel;
import com.hfad.last.network.RetrofitGetDataService;
import com.hfad.last.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    */

   private void observeViewModel(MoviesListViewModel moviesListViewModel){
       moviesListViewModel.getAllMovieList().observe(this, new Observer<MoviesListResponse>() {
           @Override
           public void onChanged(MoviesListResponse moviesListResponse) {
               if(moviesListResponse !=null ){
                    recyclerViewAdapter.addAll(moviesListResponse.getResults());
               }
           }
       });
   }

}


