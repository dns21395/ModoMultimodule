package uk.nightlines.feature.weather.container_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.weather.common.FeatureNavigationProvider
import uk.nightlines.feature.weather.one_api.ScreenApiProvider
import uk.nightlines.feature.weather.container_impl.ui.WeatherViewModel

@Component(
    dependencies = [CoreProvider::class],
    modules = [ContainerModule::class]
)
@PerFeature
internal interface ContainerComponent :
    ScreenApiProvider,
    FeatureNavigationProvider {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
        ): ContainerComponent
    }

    fun viewModel(): WeatherViewModel
}
