package uk.nightlines.feature.weather.common

import androidx.compose.runtime.compositionLocalOf

val LocalDependenciesProvider = compositionLocalOf<WeatherDependencies> {
    error("WeatherNavigation not found")
}