package service;

import android.content.Intent;

import com.example.retrofitmvvmdemoproject.model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPIService {

    @GET("movie/popular")
    Call<MovieApiResponse> getMovies(@Query("api_key") String apikey);

    @GET("movie/popular")
    Call<MovieApiResponse> getMoviesWithPaging(@Query("api_key") String apikey, @Query("page")long page);

}
