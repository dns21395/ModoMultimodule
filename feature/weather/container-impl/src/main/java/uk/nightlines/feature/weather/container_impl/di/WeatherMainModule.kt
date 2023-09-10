package uk.nightlines.feature.weather.container_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.day_api.DayScreenApi
import uk.nightlines.feature.weather.day_impl.DayImpl
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.week_api.WeekScreenApi
import uk.nightlines.feature.weather.week_impl.WeekImpl

@Module
internal class WeatherMainModule {

    @PerFeature
    @Provides
    @WeatherNavigationQualifier
    fun provideNavigation() = NavigationTypeCommand()

    @PerFeature
    @Provides
    fun provideDayScreenApi(): DayScreenApi = DayImpl()

    @PerFeature
    @Provides
    fun provideWeekScreenApi(): WeekScreenApi = WeekImpl()
}