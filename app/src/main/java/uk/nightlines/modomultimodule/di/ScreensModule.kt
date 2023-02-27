package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.feature.settings.main_api.SettingsApi
import uk.nightlines.feature.settings.main_impl.SettingsImpl
import uk.nightlines.feature.weather.main_api.WeatherApi
import uk.nightlines.feature.weather.main_impl.WeatherImpl
import javax.inject.Singleton

@Module
class ScreensModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi = WeatherImpl()

    @Provides
    @Singleton
    fun provideSettingsApi(): SettingsApi = SettingsImpl()
}