package uk.nightlines.feature.weather.one_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.weather.common.FeatureDependencies
import uk.nightlines.feature.weather.one_impl.ui.DayViewModel

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        FeatureDependencies::class
    ],
    modules = [DayModule::class]
)
internal interface DayComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            featureDependencies: FeatureDependencies
        ): DayComponent
    }

    fun viewModel(): DayViewModel
}