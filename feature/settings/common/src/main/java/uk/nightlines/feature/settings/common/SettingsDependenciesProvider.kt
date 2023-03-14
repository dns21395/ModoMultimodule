package uk.nightlines.feature.settings.common

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDependenciesProvider = staticCompositionLocalOf<SettingsDependencies> {
    error("WeatherNavigation not found")
}