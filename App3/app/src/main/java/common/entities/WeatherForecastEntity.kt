package com.example.vit_dapp.common.entities

data class WeatherForecastEntity(
    val timezone: String,
    val current: Current,
    val hourly: List<Forecast>,
    )
