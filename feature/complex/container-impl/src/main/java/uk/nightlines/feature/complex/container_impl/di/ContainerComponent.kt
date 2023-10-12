package uk.nightlines.feature.complex.container_impl.di

import dagger.Component
import uk.nightlines.core.di.di.CoreProvider
import uk.nightlines.core.di.di.FeatureScope
import uk.nightlines.feature.complex.common.FeatureNavigationProvider
import uk.nightlines.feature.complex.container_impl.ui.ComplexViewModel
import uk.nightlines.feature.complex.feature_api.ComplexFeatureApiProvider

@Component(
    dependencies = [CoreProvider::class],
    modules = [ContainerModule::class]
)
@FeatureScope
internal interface ContainerComponent :
    ComplexFeatureApiProvider,
    FeatureNavigationProvider {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
        ): ContainerComponent
    }

    fun viewModel(): ComplexViewModel
}
