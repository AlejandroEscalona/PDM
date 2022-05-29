package com.example.vit_dapp.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vit_dapp.R
import com.example.vit_dapp.mainModule.model.MainRepository
import com.example.vit_dapp.common.entities.WeatherForecastEntity
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){
    private val repository = MainRepository()

    private val result = MutableLiveData<WeatherForecastEntity>()
    fun getResult(): LiveData<WeatherForecastEntity> = result

    private val snackbarMessage = MutableLiveData<Int>()
    fun getSnackbarMessage(): LiveData<Int> = snackbarMessage

    private val loaded = MutableLiveData<Boolean>()
    fun isLoaded(): LiveData<Boolean> = loaded

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String,
                                      units: String, lang: String){
        viewModelScope.launch {
            try {
                loaded.value = false
                val resultServer = repository.getWeatherAndForecast(lat, lon, appId, units, lang)
                result.value = resultServer
            } catch (e: Exception) {
                snackbarMessage.value = R.string.main_error_message
            } finally {
                loaded.value = true
            }

        }
    }
}