package uk.nightlines.modomultimodule.di

import androidx.compose.runtime.compositionLocalOf
import uk.nightlines.core.navigation.command.NavigationTypeCommand

interface NavigationProvider {

    fun getNavigation(): NavigationTypeCommand
}

val LocalNavigationProvider = compositionLocalOf<NavigationProvider> {
    error("AppDependenciesProvider not found")
}