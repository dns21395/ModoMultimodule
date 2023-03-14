package uk.nightlines.feature.settings.common

import uk.nightlines.core.navigation.Navigation

interface SettingsDependencies {
    @SettingsNavigationQualifier
    fun getSettingsNavigation(): Navigation

    fun getSettingsScreen(): SettingsScreens
}