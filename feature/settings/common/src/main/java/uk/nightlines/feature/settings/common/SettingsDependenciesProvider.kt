package uk.nightlines.feature.settings.common

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDependenciesProvider = staticCompositionLocalOf<SetStackDependencies> {
    error("WeatherNavigation not found")
}