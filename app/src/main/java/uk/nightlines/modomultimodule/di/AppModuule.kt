package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.common.RootScreensCounterInteractor
import uk.nightlines.core.navigation.type.NavigationTypeCommand
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.modomultimodule.RootScreenImpl
import uk.nightlines.modomultimodule.domain.interactor.WeatherScreenCounterInteractorImpl
import javax.inject.Singleton

@Module
internal class AppModuule {

    @Provides
    @Singleton
    @RootNavigationQualifier
    fun provideNavigationTypeCommand(): NavigationTypeCommand = NavigationTypeCommand()

    @Provides
    @Singleton
    fun provideRootScreensInteractor(rootScreenImpl: RootScreenImpl): RootScreensInteractor = rootScreenImpl

    @Provides
    @Singleton
    fun provideRootScreensCounterInteractor(): RootScreensCounterInteractor =
        WeatherScreenCounterInteractorImpl()
}