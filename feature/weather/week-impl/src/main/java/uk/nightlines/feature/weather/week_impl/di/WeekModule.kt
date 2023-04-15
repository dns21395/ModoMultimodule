package uk.nightlines.feature.weather.week_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.common.WeatherNavigationProvider
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.day_api.DayScreenApi
import uk.nightlines.feature.weather.day_api.DayApiProvider

@Module
internal class WeekModule {

    @FeatureScope
    @Provides
    @WeatherNavigationQualifier
    fun provideNavigationTypeCommand(weatherDependencies: WeatherDependencies): NavigationTypeCommand {
        return (weatherDependencies as WeatherNavigationProvider).provideNavigation()
    }

    @FeatureScope
    @Provides
    fun provideDayApi(weatherDependencies: WeatherDependencies): DayScreenApi =
        (weatherDependencies as DayApiProvider).dayApi()
}
