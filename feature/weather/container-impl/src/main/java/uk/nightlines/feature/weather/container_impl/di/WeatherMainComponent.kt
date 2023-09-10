package uk.nightlines.feature.weather.container_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.weather.common.WeatherNavigationProvider
import uk.nightlines.feature.weather.day_api.DayApiProvider
import uk.nightlines.feature.weather.container_impl.ui.WeatherViewModel
import uk.nightlines.feature.weather.week_api.WeekApiProvider

@Component(
    dependencies = [CoreProvider::class],
    modules = [WeatherMainModule::class]
)
@PerFeature
internal interface WeatherMainComponent :
    DayApiProvider,
    WeekApiProvider,
    WeatherNavigationProvider {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
        ): WeatherMainComponent
    }

    fun viewModel(): WeatherViewModel
}
