package com.example.vit_dapp.mainModule.model

import com.example.vit_dapp.common.entities.WeatherForecastEntity

class MainRepository {
    private val remoteDatabase = RemoteDatabase()

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String, units: String,
                                      lang: String): WeatherForecastEntity =
        remoteDatabase.getWeatherForecastByCoordinates(lat,lon,appId, units, lang)
}