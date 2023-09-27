package uk.nightlines.feature.weather.common

import uk.nightlines.core.navigation.command.NavigationTypeCommand

interface FeatureNavigationProvider {

    @FeatureNavigationQualifier
    fun provideNavigation(): NavigationTypeCommand
}