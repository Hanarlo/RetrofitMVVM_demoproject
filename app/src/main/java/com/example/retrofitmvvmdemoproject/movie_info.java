package com.example.retrofitmvvmdemoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import model.Result;

public class movie_info extends AppCompatActivity {

    private Result result;
    private ImageView imageView;
    private String posterPath;
    private TextView overwiew;
    private TextView title;
    private TextView votes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        imageView = findViewById(R.id.PosterIV);
        overwiew = findViewById(R.id.OverwiewTV);
        title = findViewById(R.id.TitleInfo);
        votes = findViewById(R.id.voteCountTV);



        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("movie_data")){
            result = intent.getParcelableExtra("movie_data");
            posterPath = "https://image.tmdb.org/t/p/w500/" + result.getPosterPath();
            Glide.with(this).load(posterPath).placeholder(R.drawable.progress_circle).into(imageView);
            overwiew.setText(result.getOverview());
            title.setText(result.getOriginalTitle());
            votes.setText("Votes: " + result.getVoteCount().toString());

        }
        Toast.makeText(this, result.getOriginalTitle(),Toast.LENGTH_LONG).show();
    }
}