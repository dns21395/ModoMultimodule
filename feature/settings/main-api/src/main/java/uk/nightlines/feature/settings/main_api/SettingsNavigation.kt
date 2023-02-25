package uk.nightlines.feature.settings.main_api

import androidx.compose.runtime.compositionLocalOf
import uk.nightlines.core.navigation.Navigation

interface SettingsNavigation {

    fun getNavigation(): Navigation
}

val LocalSettingsNavigationProvider = compositionLocalOf<Navigation> {
    error("SettingsNavigation not found")
}