package com.example.ud6.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    lateinit var service: WeatherAPI

    val URL = "https://api.openweathermap.org/data/3.0/"
    val api_key = "a302190d1f44d812cbe6917df067ec02"

    init {
        initService()
    }

    private fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(WeatherAPI::class.java)
    }
}