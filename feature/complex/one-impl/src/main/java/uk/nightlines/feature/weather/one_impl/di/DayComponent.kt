package uk.nightlines.feature.weather.one_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.one_impl.ui.DayViewModel

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        WeatherDependencies::class
    ],
    modules = [DayModule::class]
)
internal interface DayComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            weatherDependencies: WeatherDependencies
        ): DayComponent
    }

    fun viewModel(): DayViewModel
}