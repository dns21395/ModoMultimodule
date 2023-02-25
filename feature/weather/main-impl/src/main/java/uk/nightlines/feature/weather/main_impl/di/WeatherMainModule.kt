package uk.nightlines.feature.weather.main_impl.di

import dagger.Component
import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.Navigation


@Module
internal class WeatherMainModule {

    @PerFeature
    @Provides
    fun provideNavigation() = Navigation()
}