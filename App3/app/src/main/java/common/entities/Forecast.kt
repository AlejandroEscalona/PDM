package com.example.vit_dapp.common.entities

data class Forecast(
    val dt: Long,
    val humidity: Int,
    val uvi: Double,
    val weather: List<Weather>,
    val pop: Double,
):
    WeatherBase(dt, humidity, uvi, weather)
