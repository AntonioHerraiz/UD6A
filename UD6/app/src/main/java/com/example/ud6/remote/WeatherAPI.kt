package com.example.ud6.remote

import com.example.ud6.models.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = RetrofitService.api_key,
        @Query("units") units: String = "metric"
    ): Response<WeatherModel?>
}