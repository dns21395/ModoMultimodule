package uk.nightlines.feature.weather.main_impl.di

import dagger.Component
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.main_impl.ui.WeatherViewModel

@Component(
    modules = [WeatherMainModule::class]
)
@PerFeature
internal interface WeatherMainComponent : WeatherDependencies {

    fun viewModel(): WeatherViewModel
}
