package uk.nightlines.feature.settings.main_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.settings.common.NavigationTypeSetStackProvider
import uk.nightlines.feature.settings.common.SetStackDependencies
import uk.nightlines.feature.settings.main_impl.SettingsViewModel
import uk.nightlines.feature.settings.one_api.SettingsOneApiProvider
import uk.nightlines.feature.settings.two_api.TwoApiProvider

@Component(
    dependencies = [CoreProvider::class],
    modules = [SettingsModule::class]
)
@PerFeature
internal interface SettingsComponent :
    SettingsOneApiProvider,
    TwoApiProvider,
    NavigationTypeSetStackProvider {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider
        ): SettingsComponent
    }

    fun viewModel(): SettingsViewModel
}