package tech.coderbot.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import tech.coderbot.model.Weather

interface WeatherAPIs {

    private val apiKey: String
        get() = "92c8fc9f16eeee3a87ce33bffc3d939a"

    private val country: String
        get() = "cairo"

    private val days: String
        get() = "7"

    private val measuringUnits: String
        get() = "metric"

    @GET("daily")
    suspend fun getWeekWeather(
        @Query("q") city: String = country,
        @Query("cnt") count: String = days,
        @Query("units") units: String = measuringUnits,
        @Query("appid") appId: String = apiKey
    ): Weather
}