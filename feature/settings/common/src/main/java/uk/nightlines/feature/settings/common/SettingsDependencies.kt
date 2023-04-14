package uk.nightlines.feature.settings.common

import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack

interface SettingsDependencies {
    @SettingsNavigationQualifier
    fun getSettingsNavigation(): NavigationTypeSetStack

    fun getSettingsScreen(): SettingsScreens
}