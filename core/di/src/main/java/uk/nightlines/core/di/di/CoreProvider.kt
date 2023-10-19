package uk.nightlines.core.di.di

import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.Navigation

interface CoreProvider {

    @RootNavigationQualifier
    fun rootNavigation(): Navigation

    fun rootScreens(): RootScreensInteractor
}