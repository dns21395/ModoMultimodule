package uk.nightlines.feature.weather.common

import androidx.compose.runtime.staticCompositionLocalOf
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.stack.StackScreen

val LocalDependenciesProvider = staticCompositionLocalOf<WeatherDependencies> {
    error("WeatherNavigation not found")
}