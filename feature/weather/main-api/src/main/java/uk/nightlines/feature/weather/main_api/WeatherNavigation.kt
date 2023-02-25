package uk.nightlines.feature.weather.main_api

import androidx.compose.runtime.compositionLocalOf
import uk.nightlines.core.navigation.Navigation

interface WeatherNavigation {

    fun getNavigation(): Navigation
}

val LocalWeatherNavigationProvider = compositionLocalOf<Navigation> {
    error("WeatherNavigation not found")
}