package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.common.WeatherScreenCounterInteractor
import uk.nightlines.core.navigation.type.NavigationTypeCommand
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.RootScreens
import uk.nightlines.modomultimodule.RootScreenImpl
import uk.nightlines.modomultimodule.domain.interactor.WeatherScreenCounterInteractorImpl
import javax.inject.Singleton

@Module
internal class AppModuule {

    @Provides
    @Singleton
    @RootNavigationQualifier
    fun provideNavigation(): NavigationTypeCommand = NavigationTypeCommand()

    @Provides
    @Singleton
    fun provideRootScreens(rootScreenImpl: RootScreenImpl): RootScreens = rootScreenImpl

    @Provides
    @Singleton
    fun provideWeatherScreenCounterInteractor(): WeatherScreenCounterInteractor =
        WeatherScreenCounterInteractorImpl()
}