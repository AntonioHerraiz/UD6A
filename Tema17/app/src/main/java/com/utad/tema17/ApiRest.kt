package com.utad.tema17

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRest {
    lateinit var service: ApiService
    val URL = "https://api.themoviedb.org/3/"
    val URL_IMAGES = "https://image.tmdb.org/t/p/w500/"
    val apiKey = "30ee4bab2bf9164bbcce9b35aa68f5fc"
    val language = "es-ES"
    fun initService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ApiService::class.java)
    }

}