package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.common.WeatherScreenCounterInteractor
import uk.nightlines.core.navigation.Navigation
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
    fun provideNavigation(): Navigation = Navigation()

    @Provides
    @Singleton
    fun provideRootScreens(rootScreenImpl: RootScreenImpl): RootScreens = rootScreenImpl

    @Provides
    @Singleton
    fun provideWeatherScreenCounterInteractor(): WeatherScreenCounterInteractor =
        WeatherScreenCounterInteractorImpl()
}