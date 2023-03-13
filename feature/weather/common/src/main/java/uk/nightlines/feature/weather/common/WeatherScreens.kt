package uk.nightlines.feature.weather.common

import com.github.terrakok.modo.Screen

interface WeatherScreens {

    fun getDayScreen(): Screen

    fun getWeekScreen(): Screen
}