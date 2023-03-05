package uk.nightlines.feature.weather.common

import com.github.terrakok.modo.Screen

interface WeatherScreenInteractor {

    fun getDayScreen(): Screen

    fun getWeekScreen(): Screen
}