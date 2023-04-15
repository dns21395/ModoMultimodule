package uk.nightlines.feature.weather.week_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.week_impl.ui.WeekViewModel

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        WeatherDependencies::class
    ],
    modules = [WeekModule::class]
)
internal interface WeekComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            weatherDependencies: WeatherDependencies
        ): WeekComponent
    }

    fun viewModel(): WeekViewModel
}