package uk.nightlines.feature.settings.common

import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack

interface NavigationTypeSetStackProvider : WeatherDependencies {

    @SetStackNavigationQualifier
    fun provideNavigationTypeSetStack(): NavigationTypeSetStack
}