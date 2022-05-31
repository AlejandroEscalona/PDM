package com.example.vit_dapp.common.utils
import com.example.vit_dapp.common.entities.Weather
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {
    fun getHour(epoch: Long): String = getFormatedtime(epoch, "HH:mm")

    fun getFullDate(epoch: Long): String = getFormatedtime(epoch, "dd/MM/yy HH:mm")


    fun getFormatedtime(epoch: Long, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(epoch * 1_000)
    }

    fun getWeatherMain(weather: List<Weather>?): String{
        return if (weather != null && weather.isNotEmpty() ) {
            weather[0].main
        } else {
            "-"
        }
    }

    fun getWeatherDescription(weather: List<Weather>?): String{
        return if (weather != null && weather.isNotEmpty()) {
            weather[0].description
        } else {
            "-"
        }
    }
}