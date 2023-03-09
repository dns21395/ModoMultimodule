package uk.nightlines.feature.weather.main_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.main_api.WeatherApi
import uk.nightlines.feature.weather.main_impl.ui.WeatherStack

class WeatherImpl : WeatherApi {

    override fun getWeatherScreen(counter: Int): Screen {
        return WeatherStack(counter)
    }
}