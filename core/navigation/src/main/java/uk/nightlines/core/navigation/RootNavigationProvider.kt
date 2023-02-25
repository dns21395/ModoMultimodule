package uk.nightlines.core.navigation

import androidx.compose.runtime.compositionLocalOf

interface RootNavigationProvider {

    fun getNavigation(): Navigation
}

val LocalNavigationProvider = compositionLocalOf<RootNavigationProvider> {
    error("AppDependenciesProvider not found")
}