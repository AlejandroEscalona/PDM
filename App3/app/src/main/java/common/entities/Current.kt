package com.example.vit_dapp.common.entities

data class Current(
    val dt: Long,
    val humidity: Int,
    val uvi: Double,
    val weather: List<Weather>,
    val sunrise: Long,
    ):
    WeatherBase(dt, humidity, uvi, weather)
