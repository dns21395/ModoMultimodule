package uk.nightlines.feature.settings.common

import uk.nightlines.core.navigation.NavigationStackList

interface SettingsDependencies {
    @SettingsNavigationQualifier
    fun getSettingsNavigation(): NavigationStackList

    fun getSettingsScreen(): SettingsScreens
}