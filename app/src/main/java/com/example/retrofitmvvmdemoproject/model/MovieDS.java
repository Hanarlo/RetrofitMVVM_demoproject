package com.example.retrofitmvvmdemoproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.retrofitmvvmdemoproject.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import service.MovieAPIService;
import service.RetrofitInstance;

public class MovieDS extends PageKeyedDataSource<Long, Result> {

    private MovieAPIService movieAPIService;
    private Application application;

    public MovieDS(MovieAPIService movieAPIService, Application application) {
        this.movieAPIService = movieAPIService;
        this.application = application;
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> loadParams, @NonNull final LoadCallback<Long, Result> loadCallback) {
        movieAPIService = RetrofitInstance.getService();
        Call<MovieApiResponse> call = movieAPIService.getMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key),loadParams.key);
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if (movieApiResponse != null && movieApiResponse.getResults() !=null){
                    results = (ArrayList<Result>) movieApiResponse.getResults();

                    loadCallback.onResult(results,  loadParams.key+1);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> loadParams, @NonNull LoadCallback<Long, Result> loadCallback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> loadInitialParams, @NonNull LoadInitialCallback<Long, Result> loadInitialCallback) {
        movieAPIService = RetrofitInstance.getService();
        Call<MovieApiResponse> call = movieAPIService.getMoviesWithPaging(application.getApplicationContext().getString(R.string.api_key),1);
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieApiResponse = response.body();
                ArrayList<Result> results = new ArrayList<>();
                if (movieApiResponse != null && movieApiResponse.getResults() !=null){
                    results = (ArrayList<Result>) movieApiResponse.getResults();
                    loadInitialCallback.onResult(results, null, (long) 2);
                }

            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
}}
