package uk.nightlines.feature.weather.one_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.common.WeatherNavigationProvider
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.two_api.WeekApiProvider
import uk.nightlines.feature.weather.two_api.WeekScreenApi

@Module
internal class DayModule {

    @FeatureScope
    @Provides
    @WeatherNavigationQualifier
    fun provideNavigation(weatherDependencies: WeatherDependencies): NavigationTypeCommand {
        return (weatherDependencies as WeatherNavigationProvider).provideNavigation()
    }

    @FeatureScope
    @Provides
    fun provideWeekScreenApi(weatherDependencies: WeatherDependencies): WeekScreenApi {
        return (weatherDependencies as WeekApiProvider).weekScreenApi()
    }
}