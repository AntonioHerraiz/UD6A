package com.example.ud6.weatherlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ud6.models.WeatherModel
import com.example.ud6.remote.RetrofitService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherListViewModel: ViewModel() {

    val weatherModel = MutableStateFlow<WeatherModel?>(null)

    fun getWeather(lat: String = "40.4165", lon: String = "-3.70256") {
        viewModelScope.launch {
            val response = RetrofitService.service.getWeather(lat, lon)
            if (response.isSuccessful && response.body() != null) {
                weatherModel.value = response.body()!!
            } else {
                Log.e("WeatherListViewModel", "getWeather: ${response.errorBody()?.string()}")
            }
        }
    }

}