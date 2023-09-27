package uk.nightlines.feature.weather.two_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.weather.common.FeatureDependencies
import uk.nightlines.feature.weather.two_impl.ui.WeekViewModel

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        FeatureDependencies::class
    ],
    modules = [WeekModule::class]
)
internal interface WeekComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            featureDependencies: FeatureDependencies
        ): WeekComponent
    }

    fun viewModel(): WeekViewModel
}