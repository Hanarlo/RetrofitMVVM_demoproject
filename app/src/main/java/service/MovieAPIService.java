package service;

import model.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPIService {

    @GET("movie/popular")
    Call<MovieApiResponse> getMovies(@Query("api_key") String apikey);

}
