package uk.nightlines.feature.settings.common

import uk.nightlines.core.navigation.type.NavigationTypeSetStack

interface SettingsDependencies {
    @SettingsNavigationQualifier
    fun getSettingsNavigation(): NavigationTypeSetStack

    fun getSettingsScreen(): SettingsScreens
}