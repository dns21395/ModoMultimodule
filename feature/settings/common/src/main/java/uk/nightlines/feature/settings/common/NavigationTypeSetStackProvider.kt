package uk.nightlines.feature.settings.common

import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack

interface NavigationTypeSetStackProvider : SetStackDependencies {

    @SetStackNavigationQualifier
    fun provideNavigationTypeSetStack(): NavigationTypeSetStack
}