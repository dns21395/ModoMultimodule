package uk.nightlines.core.di

import uk.nightlines.core.common.RootScreensCounterInteractor
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.common.RootScreensInteractor

interface CoreProvider {

    @RootNavigationQualifier
    fun getRootNavigation(): NavigationTypeCommand

    fun rootScreens(): RootScreensInteractor

    fun rootScreensCounterInteractor(): RootScreensCounterInteractor
}