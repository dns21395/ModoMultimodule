package uk.nightlines.feature.weather.common

import androidx.compose.runtime.staticCompositionLocalOf

interface FeatureDependencies

val LocalDependenciesProvider = staticCompositionLocalOf<FeatureDependencies> {
    error("FeatureDependencies not found")
}