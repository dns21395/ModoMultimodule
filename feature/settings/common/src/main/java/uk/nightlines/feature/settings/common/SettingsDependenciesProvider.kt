package uk.nightlines.feature.settings.common

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDependenciesProvider = staticCompositionLocalOf<WeatherDependencies> {
    error("WeatherNavigation not found")
}