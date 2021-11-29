package com.example.retrofitmvvmdemoproject.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofitmvvmdemoproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.MovieAPIService;
import service.retrofitInstance;

public class MovieRepository {

    private ArrayList<Result> results = new ArrayList<>();

    public MovieRepository(Application application) {
        this.application = application;
    }

    private Application application;
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Result>> getMutableLiveData() {

        MovieAPIService movieAPIService = retrofitInstance.getService();

        Call<MovieApiResponse> call = movieAPIService.getMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                MovieApiResponse movieAPIResponce = response.body();

                if (movieAPIResponce != null && movieAPIResponce.getResults() != null){
                    results = (ArrayList<Result>) movieAPIResponce.getResults();
                    mutableLiveData.setValue(results);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
