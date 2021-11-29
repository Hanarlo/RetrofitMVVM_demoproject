package com.example.retrofitmvvmdemoproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PagedListConfigKt;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.example.retrofitmvvmdemoproject.model.MovieDS;
import com.example.retrofitmvvmdemoproject.model.MovieDSFactory;
import com.example.retrofitmvvmdemoproject.model.MovieRepository;
import com.example.retrofitmvvmdemoproject.model.Result;

import service.MovieAPIService;
import service.RetrofitInstance;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<MovieDS> movieDSLiveData;
    private Executor executor;
    private LiveData<PagedList<Result>> pagedListLiveData;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);

        MovieAPIService movieAPIService = RetrofitInstance.getService();
        MovieDSFactory movieDSFactory = new MovieDSFactory(application,movieAPIService);

        movieDSLiveData = movieDSFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(true).setInitialLoadSizeHint(5).setPageSize(10).setPrefetchDistance(2).build();
        executor = Executors.newCachedThreadPool();

        pagedListLiveData = new LivePagedListBuilder<Long, Result>(movieDSFactory, config).setFetchExecutor(executor).build();

    }

    public LiveData<List<Result>> getAllMovieData(){



        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Result>> getPagedListLiveData(){
        return pagedListLiveData;
    }
}
