package uk.nightlines.feature.weather.common

import uk.nightlines.core.navigation.command.NavigationTypeCommand

interface WeatherNavigationProvider {

    @WeatherNavigationQualifier
    fun provideNavigation(): NavigationTypeCommand
}