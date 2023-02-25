package uk.nightlines.modomultimodule.di

import androidx.compose.runtime.compositionLocalOf
import uk.nightlines.core.navigation.Navigation

interface NavigationProvider {

    fun getNavigation(): Navigation
}

val LocalNavigationProvider = compositionLocalOf<NavigationProvider> {
    error("AppDependenciesProvider not found")
}