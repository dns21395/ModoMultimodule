package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.feature.settings.api.SettingsApi
import uk.nightlines.feature.settings.impl.SettingsImpl
import uk.nightlines.feature.weather.container_api.ComplexApi
import uk.nightlines.feature.weather.container_impl.WeatherImpl
import javax.inject.Singleton

@Module
class ScreensModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): ComplexApi = WeatherImpl()

    @Provides
    @Singleton
    fun provideSettingsApi(): SettingsApi = SettingsImpl()
}