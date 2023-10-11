package uk.nightlines.feature.complex.common

import uk.nightlines.core.navigation.command.NavigationTypeCommand

interface FeatureNavigationProvider {

    @FeatureNavigationQualifier
    fun provideNavigation(): NavigationTypeCommand
}