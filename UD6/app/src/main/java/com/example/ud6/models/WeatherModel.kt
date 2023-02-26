package com.example.ud6.models

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("current") val current: Current,
    @SerializedName("daily") val dailyList: List<DailyItem>
)

data class Current(
    @SerializedName("temp") val temp: Float,
    @SerializedName("humidity") val humidity: Float,
    @SerializedName("weather") val weather: List<Weather>
)

data class DailyItem(
    @SerializedName("temp") val temp: Temp,
    @SerializedName("humidity") val humidity: Float,
    @SerializedName("weather") val weather: List<Weather>
)

data class Temp(
    @SerializedName("day") val dayTemp: Float
)

data class Weather(
    @SerializedName("icon") val icon: String
)