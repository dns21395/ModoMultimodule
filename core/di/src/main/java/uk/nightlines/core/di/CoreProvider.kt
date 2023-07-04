package uk.nightlines.core.di

import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand

interface CoreProvider {

    @RootNavigationQualifier
    fun getRootNavigation(): NavigationTypeCommand

    fun rootScreens(): RootScreensInteractor
}