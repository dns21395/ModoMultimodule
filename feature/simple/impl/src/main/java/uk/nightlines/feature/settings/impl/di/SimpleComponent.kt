package uk.nightlines.feature.settings.impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.settings.impl.ui.SimpleViewModel

@Component(
    dependencies = [CoreProvider::class]
)
@PerFeature
internal interface SimpleComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider
        ): SimpleComponent
    }

    fun viewModel(): SimpleViewModel
}