package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.simple.api.SimpleApi
import uk.nightlines.feature.simple.impl.SimpleImpl
import uk.nightlines.feature.weather.container_api.ComplexApi
import uk.nightlines.feature.weather.container_impl.ComplexImpl
import uk.nightlines.modomultimodule.navigation.RootScreenImpl
import javax.inject.Singleton

@Module
internal class AppModuule {

    @Provides
    @Singleton
    @RootNavigationQualifier
    fun provideNavigationTypeCommand(): NavigationTypeCommand = NavigationTypeCommand()

    @Provides
    @Singleton
    fun provideRootScreensInteractor(impl: RootScreenImpl): RootScreensInteractor = impl

    @Provides
    @Singleton
    fun provideWeatherApi(): ComplexApi = ComplexImpl()

    @Provides
    @Singleton
    fun provideSettingsApi(): SimpleApi = SimpleImpl()
}