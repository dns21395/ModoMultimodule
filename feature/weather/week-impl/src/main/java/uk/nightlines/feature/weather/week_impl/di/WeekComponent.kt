package uk.nightlines.feature.weather.week_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.weather.main_api.WeatherDependencies

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        WeatherDependencies::class
    ]
)
interface WeekComponent : CoreProvider, WeatherDependencies {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            settingsDependencies: WeatherDependencies
        ): WeekComponent
    }
}