package uk.nightlines.core.di

import uk.nightlines.core.navigation.RootNavigationProvider
import uk.nightlines.core.navigation.RootScreens

interface CoreProvider : RootNavigationProvider {

    fun rootScreens(): RootScreens
}