package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.RootScreens
import uk.nightlines.feature.weather.main_api.WeatherApi
import uk.nightlines.feature.weather.main_impl.WeatherImpl
import uk.nightlines.modomultimodule.RootScreenImpl
import javax.inject.Singleton

@Module
class AppModuule {

    @Provides
    @Singleton
    fun provideNavigation(): Navigation = Navigation()

    @Provides
    @Singleton
    fun provideRootScreens(rootScreenImpl: RootScreenImpl): RootScreens = rootScreenImpl
}