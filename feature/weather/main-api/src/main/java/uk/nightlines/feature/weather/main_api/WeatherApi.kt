package uk.nightlines.feature.weather.main_api

import com.github.terrakok.modo.Screen

interface WeatherApi {

    fun getWeatherScreen(counter: Int): Screen
}