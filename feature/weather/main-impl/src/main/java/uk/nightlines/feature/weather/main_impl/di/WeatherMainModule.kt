package uk.nightlines.feature.weather.main_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.feature.weather.day_api.DayApi
import uk.nightlines.feature.weather.day_impl.DayImpl
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherScreens
import uk.nightlines.feature.weather.main_impl.domain.interactor.ScreensImpl
import uk.nightlines.feature.weather.week_api.WeekApi
import uk.nightlines.feature.weather.week_impl.WeekImpl

@Module
internal class WeatherMainModule {

    @PerFeature
    @Provides
    @WeatherNavigationQualifier
    fun provideNavigation() = Navigation()

    @PerFeature
    @Provides
    fun provideDayApi(): DayApi = DayImpl()

    @PerFeature
    @Provides
    fun provideWeekApi(): WeekApi = WeekImpl()

    @PerFeature
    @Provides
    fun provideWeatherScreens(
        screenInteractorImpl: ScreensImpl,
    ): WeatherScreens {
        return screenInteractorImpl
    }
}