package tech.coderbot.weather.application

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tech.coderbot.data.repository.WeatherRepositoryImplementation
import tech.coderbot.domain.repository.WeatherRepository
import tech.coderbot.domain.usecase.GetWeather
import tech.coderbot.weather.home.HomeViewModel

val koinModule = module {

    single<WeatherRepository> { WeatherRepositoryImplementation() }

    single { GetWeather(get()) }

    viewModel { HomeViewModel(get()) }
}