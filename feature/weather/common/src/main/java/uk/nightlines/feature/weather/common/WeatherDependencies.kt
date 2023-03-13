package uk.nightlines.feature.weather.common

import uk.nightlines.core.navigation.Navigation

interface WeatherDependencies {

    @WeatherNavigationQualifier
    fun getWeatherNavigation(): Navigation

    fun weatherScreens(): WeatherScreens
}