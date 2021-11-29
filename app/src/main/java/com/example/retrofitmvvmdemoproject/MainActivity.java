package com.example.retrofitmvvmdemoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.paging.PagedListConfigKt;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.retrofitmvvmdemoproject.databinding.ActivityMainBinding;
import com.example.retrofitmvvmdemoproject.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import adapter.resultAdapter;

import com.example.retrofitmvvmdemoproject.model.Result;

public class MainActivity extends AppCompatActivity {

    private PagedList<Result> results;
    private adapter.resultAdapter resultAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);
        swipeRefreshLayout = activityMainBinding.swiperefresh;
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

        mainActivityViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> resultList) {
                results = resultList;
                fillRecyclerView();
            }
        });


    }

    private void fillRecyclerView() {
        recyclerView = activityMainBinding.recyclerView;
        resultAdapter = new resultAdapter(this);
        resultAdapter.submitList(results );
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