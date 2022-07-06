package tech.coderbot.domain.repository

import tech.coderbot.model.Weather

interface WeatherRepository {

    suspend fun getWeekWeather(): Weather
}