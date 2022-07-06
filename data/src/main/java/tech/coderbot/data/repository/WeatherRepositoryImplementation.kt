package tech.coderbot.data.repository

import tech.coderbot.data.api.WeatherAPIs
import tech.coderbot.data.base.Repository
import tech.coderbot.domain.repository.WeatherRepository
import tech.coderbot.model.Weather

class WeatherRepositoryImplementation: Repository(), WeatherRepository {

    private val api = retrofit().create(WeatherAPIs::class.java)

    override suspend fun getWeekWeather(): Weather = api.getWeekWeather()
}