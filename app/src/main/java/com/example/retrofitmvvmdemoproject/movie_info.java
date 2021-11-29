package com.example.retrofitmvvmdemoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.retrofitmvvmdemoproject.databinding.ActivityMovieInfoBinding;
import com.example.retrofitmvvmdemoproject.model.Result;

public class movie_info extends AppCompatActivity {

    private Result result;
    private ActivityMovieInfoBinding activityMovieInfoBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        activityMovieInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_info);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("movie_data")){
            result = intent.getParcelableExtra("movie_data");
            activityMovieInfoBinding.setResult(result);
        }
    }
}