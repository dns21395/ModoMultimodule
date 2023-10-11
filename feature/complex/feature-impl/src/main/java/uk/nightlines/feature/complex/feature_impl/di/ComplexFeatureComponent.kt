package uk.nightlines.feature.complex.feature_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.complex.common.FeatureDependencies
import uk.nightlines.feature.complex.feature_impl.ui.ComplexFeatureViewModel

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        FeatureDependencies::class
    ],
    modules = [ComplexFeatureModule::class]
)
internal interface ComplexFeatureComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            featureDependencies: FeatureDependencies
        ): ComplexFeatureComponent
    }

    fun viewModel(): ComplexFeatureViewModel
}