package uk.nightlines.feature.weather.container_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.weather.common.FeatureNavigationProvider
import uk.nightlines.feature.weather.one_api.DayApiProvider
import uk.nightlines.feature.weather.container_impl.ui.WeatherViewModel
import uk.nightlines.feature.weather.two_api.WeekApiProvider

@Component(
    dependencies = [CoreProvider::class],
    modules = [ContainerModule::class]
)
@PerFeature
internal interface ContainerComponent :
    DayApiProvider,
    WeekApiProvider,
    FeatureNavigationProvider {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
        ): ContainerComponent
    }

    fun viewModel(): WeatherViewModel
}
