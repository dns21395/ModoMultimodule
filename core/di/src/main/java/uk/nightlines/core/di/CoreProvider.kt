package uk.nightlines.core.di

import uk.nightlines.core.common.WeatherScreenCounterInteractor
import uk.nightlines.core.navigation.type.NavigationTypeCommand
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.RootScreens

interface CoreProvider {

    @RootNavigationQualifier
    fun getCoreNavigation(): NavigationTypeCommand

    fun rootScreens(): RootScreens

    fun weatherScreenCounterInteractor(): WeatherScreenCounterInteractor
}