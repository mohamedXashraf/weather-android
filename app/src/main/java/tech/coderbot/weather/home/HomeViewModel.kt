package tech.coderbot.weather.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.coderbot.domain.usecase.GetWeather
import tech.coderbot.weather.base.BaseViewModel

class HomeViewModel constructor(private val getWeather: GetWeather) : BaseViewModel<HomeViewState, HomeViewIntents>()
{
    init
    {
        observeIntents {
            when (it)
            {
                is HomeViewIntents.GetWeather -> getWeekWeather()
            }
        }
    }

    private fun getWeekWeather() = CoroutineScope(Dispatchers.IO).launch {
        state.postValue(HomeViewState.LoadingState)
        try
        {
            state.postValue(HomeViewState.DataState(getWeather.run()))
        }
        catch (ex: Throwable)
        {
            ex.printStackTrace()
            state.postValue(HomeViewState.ErrorState(ex))
        }
    }
}