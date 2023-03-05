package uk.nightlines.feature.weather.day_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.weather.day_impl.ui.DayViewModel
import uk.nightlines.feature.weather.main_api.WeatherDependencies

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        WeatherDependencies::class
    ]
)
internal interface DayComponent : CoreProvider, WeatherDependencies {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            weatherDependencies: WeatherDependencies
        ): DayComponent
    }

    fun viewModel(): DayViewModel
}