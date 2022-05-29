package common.dataAccess

import com.example.vit_dapp.common.entities.WeatherForecastEntity
import com.example.vit_dapp.common.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET(Constants.ONE_CALL_PATH)
    suspend fun getWeatherForecastByCoordinates(
        @Query(Constants.LATITUDE_PARAM) lat: Double,
        @Query(Constants.LONGITUDE_PARAM) lon: Double,
        @Query(Constants.APP_ID_PARAM) appId: String,
        @Query(Constants.UNITS_PARAM) units: String,
        @Query(Constants.LANG_PARAM) lang: String,
    ): WeatherForecastEntity
}