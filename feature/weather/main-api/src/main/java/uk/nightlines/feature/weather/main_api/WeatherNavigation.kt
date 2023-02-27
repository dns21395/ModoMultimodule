package uk.nightlines.feature.weather.main_api

import androidx.compose.runtime.compositionLocalOf
import uk.nightlines.core.navigation.Navigation

val LocalDependenciesProvider = compositionLocalOf<WeatherDependencies> {
    error("WeatherNavigation not found")
}