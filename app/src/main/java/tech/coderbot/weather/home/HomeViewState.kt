package tech.coderbot.weather.home

import tech.coderbot.model.Weather

sealed class HomeViewState
{
    object ViewInitializationState : HomeViewState()
    class DataState(val weather: Weather) : HomeViewState()
    class ErrorState(val error: Throwable) : HomeViewState()
    object LoadingState : HomeViewState()
}