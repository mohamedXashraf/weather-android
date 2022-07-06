package tech.coderbot.domain.usecase

import tech.coderbot.domain.repository.WeatherRepository

class GetWeather constructor(private val repository: WeatherRepository) {

    suspend fun run() = repository.getWeekWeather()
}