package uk.nightlines.core.di

import uk.nightlines.core.common.WeatherScreenCounterInteractor
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.RootNavigationProvider
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.RootScreens

interface CoreProvider {

    @RootNavigationQualifier
    fun getCoreNavigation(): Navigation

    fun rootScreens(): RootScreens

    fun weatherScreenCounterInteractor(): WeatherScreenCounterInteractor
}