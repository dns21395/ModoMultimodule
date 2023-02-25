package uk.nightlines.feature.weather.main_impl.di

import dagger.Component
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.weather.main_api.WeatherNavigation

@Component(
    modules = [WeatherMainModule::class]
)
@PerFeature
internal interface WeatherMainComponent : WeatherNavigation
