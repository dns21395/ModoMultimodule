package uk.nightlines.feature.settings.main_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.settings.common.SetStackDependenciesApi
import uk.nightlines.feature.settings.main_impl.SettingsViewModel

@Component(
    dependencies = [CoreProvider::class],
    modules = [SettingsModule::class]
)
@PerFeature
internal interface SettingsComponent : SetStackDependenciesApi {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider
        ): SettingsComponent
    }

    fun viewModel(): SettingsViewModel
}