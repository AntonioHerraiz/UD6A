package com.utad.tema17

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") apikey: String = ApiRest.apiKey,
        @Query("language") language: String = ApiRest.language
    ): Call<GenresResponse>

    @GET("movie/popular")
    fun getMovies(
        @Query("with_genres") with_genres: String,
        @Query("api_key") apikey: String = ApiRest.apiKey,
        @Query("language") language: String = ApiRest.language,
    ): Call<MovieResponse>
}