package uk.nightlines.feature.settings.common

import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack

interface SetStackDependenciesApi {
    @SetStackNavigationQualifier
    fun getNavigationTypeSetStack(): NavigationTypeSetStack

    fun getSetStackScreens(): SetStackScreens
}