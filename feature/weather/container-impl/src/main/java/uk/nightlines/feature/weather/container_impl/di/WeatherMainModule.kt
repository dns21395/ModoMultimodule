package uk.nightlines.feature.weather.container_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.one_api.DayScreenApi
import uk.nightlines.feature.weather.one_impl.DayImpl
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.two_api.WeekScreenApi
import uk.nightlines.feature.weather.two_impl.WeekImpl

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