package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.feature.simple.api.SimpleApi
import uk.nightlines.feature.simple.impl.SimpleImpl
import uk.nightlines.feature.weather.container_api.ComplexApi
import uk.nightlines.feature.weather.container_impl.ComplexImpl
import javax.inject.Singleton

@Module
class ScreensModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): ComplexApi = ComplexImpl()

    @Provides
    @Singleton
    fun provideSettingsApi(): SimpleApi = SimpleImpl()
}