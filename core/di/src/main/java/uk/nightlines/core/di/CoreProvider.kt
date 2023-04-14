package uk.nightlines.core.di

import uk.nightlines.core.common.RootScreensCounterInteractor
import uk.nightlines.core.navigation.type.NavigationTypeCommand
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.common.RootScreensInteractor

interface CoreProvider {

    @RootNavigationQualifier
    fun getCoreNavigation(): NavigationTypeCommand

    fun rootScreens(): RootScreensInteractor

    fun weatherScreenCounterInteractor(): RootScreensCounterInteractor
}