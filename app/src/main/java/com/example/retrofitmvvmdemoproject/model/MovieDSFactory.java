package com.example.retrofitmvvmdemoproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import service.MovieAPIService;

public class MovieDSFactory extends DataSource.Factory {

    Application application;
    MovieAPIService movieAPIService;
    MovieDS movieDS;
    MutableLiveData<MovieDS> mutableLiveData;

    public MovieDSFactory(Application application, MovieAPIService movieAPIService) {
        this.application = application;
        this.movieAPIService = movieAPIService;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDS = new MovieDS(movieAPIService, application);
        mutableLiveData.postValue(movieDS);
        return movieDS;
    }

    public MutableLiveData<MovieDS> getMutableLiveData(){
        return mutableLiveData;
    }
}
