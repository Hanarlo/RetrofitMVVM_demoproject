package com.example.retrofitmvvmdemoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.retrofitmvvmdemoproject.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import adapter.resultAdapter;
import model.MovieApiResponse;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.MovieAPIService;
import service.retrofitInstance;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> results;
    private adapter.resultAdapter resultAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.purple_200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });

        getPopularMovies();
    }

    public void getPopularMovies(){

        mainActivityViewModel.getAllMovieData().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results3) {
                results = (ArrayList<Result>) results3;
                fillRecyclerView();
            }
        });

    }

    private void fillRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        resultAdapter = new resultAdapter(this, results);

        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(resultAdapter);
        resultAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);




    }


}