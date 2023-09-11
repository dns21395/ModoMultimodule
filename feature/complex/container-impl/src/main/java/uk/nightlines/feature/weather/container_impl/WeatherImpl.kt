package uk.nightlines.feature.weather.container_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.container_api.WeatherApi
import uk.nightlines.feature.weather.container_impl.ui.WeatherStack

class WeatherImpl : WeatherApi {

    override fun weatherScreen(): Screen {
        return WeatherStack()
    }
}