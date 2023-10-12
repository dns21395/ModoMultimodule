package uk.nightlines.feature.simple.impl.di

import dagger.Component
import uk.nightlines.core.di.di.CoreProvider
import uk.nightlines.core.di.di.FeatureScope
import uk.nightlines.feature.simple.impl.ui.SimpleViewModel

@Component(
    dependencies = [CoreProvider::class]
)
@FeatureScope
internal interface SimpleComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider
        ): SimpleComponent
    }

    fun viewModel(): SimpleViewModel
}