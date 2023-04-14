package uk.nightlines.feature.settings.common

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDependenciesProvider = staticCompositionLocalOf<SetStackDependenciesApi> {
    error("WeatherNavigation not found")
}