package uk.nightlines.feature.settings.main_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.settings.main_impl.ui.SettingsViewModel

@Component(
    dependencies = [CoreProvider::class]
)
@PerFeature
internal interface SettingsComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider
        ): SettingsComponent
    }

    fun viewModel(): SettingsViewModel
}