package uk.nightlines.feature.weather.common

import uk.nightlines.core.navigation.type.NavigationTypeCommand

interface WeatherDependencies {

    @WeatherNavigationQualifier
    fun getWeatherNavigation(): NavigationTypeCommand

    fun weatherScreens(): WeatherScreens
}