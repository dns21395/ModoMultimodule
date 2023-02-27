package uk.nightlines.feature.settings.main_api

import androidx.compose.runtime.compositionLocalOf
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.feature.settings.one_api.SettingsOneApi
import uk.nightlines.feature.settings.two_api.SettingsTwoApi

interface SettingsDependencies {

    @SettingsNavigationQualifier
    fun getNavigation(): Navigation

    fun getOneApi(): SettingsOneApi

    fun getTwoApi(): SettingsTwoApi
}

val LocalSettingsDependencies = compositionLocalOf<SettingsDependencies> {
    error("SettingsNavigation not found")
}