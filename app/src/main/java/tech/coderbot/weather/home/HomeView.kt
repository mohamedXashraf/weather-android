package tech.coderbot.weather.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import tech.coderbot.model.DayWeather
import tech.coderbot.model.Weather
import tech.coderbot.weather.common.Error
import tech.coderbot.weather.common.Loading
import tech.coderbot.weather.common.TextView
import tech.coderbot.weather.ui.theme.PrimaryColor
import tech.coderbot.weather.R
import tech.coderbot.weather.common.RemoteImageView

@Composable
fun Home(viewModel: HomeViewModel = getViewModel())
{
    val state = viewModel.state.observeAsState(initial = HomeViewState.ViewInitializationState)

    Scaffold(
        modifier = Modifier.fillMaxSize().background(Color.White),
        topBar = { TopBar() }
    ) { }

    when (val viewState = state.value)
    {
        is HomeViewState.ViewInitializationState -> viewModel.intent.value = HomeViewIntents.GetWeather
        is HomeViewState.DataState -> HomeView(viewState.weather)
        is HomeViewState.ErrorState -> Error(error = viewState.error.message ?: stringResource(id = R.string.error))
        is HomeViewState.LoadingState -> Loading()
    }
}

@Composable
fun HomeView(weather: Weather = Weather())
{
    val selectedWeather = remember { mutableStateOf(weather.daysWeather[0]) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(7.dp),
        topBar = { TopBar() }
    ) {
        Column {
            LazyRow {
                items(weather.daysWeather) {
                    WeatherItemView(weather = it) { weather ->
                        selectedWeather.value = weather
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherItemDetailsView(selectedWeather.value)
            }
        }
    }
}

@Composable
private fun TopBar()
{
    TopAppBar(
        title = { TextView(text = stringResource(id = R.string.app_name), color = PrimaryColor) },
        elevation = 5.dp,
        backgroundColor = Color.White
    )
}

@Composable
private fun WeatherItemView(weather: DayWeather, action: (DayWeather) -> Unit)
{
    Card(
        backgroundColor = Color.White,
        elevation = 7.dp,
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable { action(weather) },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherItemDetailsView(weather)
        }
    }
}

@Composable
private fun WeatherItemDetailsView(weather: DayWeather) {
    RemoteImageView(weather.weather[0].icon ?: "01d")
    TextView(text = weather.weather[0].main ?: "Clear", weight = FontWeight.SemiBold)
    TextView(text = "${weather.temp?.max ?: "0"} C", size = 14)
    TextView(text = "${weather.temp?.min ?: "0"} C", size = 14)
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview()
{
    HomeView()
}