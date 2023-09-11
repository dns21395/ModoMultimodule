package uk.nightlines.feature.weather.common

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDependenciesProvider = staticCompositionLocalOf<WeatherDependencies> {
    error("WeatherNavigation not found")
}