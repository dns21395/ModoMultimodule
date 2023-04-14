package uk.nightlines.feature.weather.common

import uk.nightlines.core.navigation.command.NavigationTypeCommand

interface WeatherDependencies {

    @WeatherNavigationQualifier
    fun getWeatherNavigation(): NavigationTypeCommand

    fun weatherScreens(): WeatherScreens
}