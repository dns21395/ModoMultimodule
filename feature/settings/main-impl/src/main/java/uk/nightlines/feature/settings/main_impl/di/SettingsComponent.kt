package uk.nightlines.feature.settings.main_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.settings.common.NavigationTypeSetStackProvider
import uk.nightlines.feature.settings.main_impl.ui.SettingsViewModel
import uk.nightlines.feature.settings.one_api.OneScreenApiProvider
import uk.nightlines.feature.settings.two_api.TwoScreenApiProvider

@Component(
    dependencies = [CoreProvider::class],
    modules = [SettingsModule::class]
)
@PerFeature
internal interface SettingsComponent :
    OneScreenApiProvider,
    TwoScreenApiProvider,
    NavigationTypeSetStackProvider {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider
        ): SettingsComponent
    }

    fun viewModel(): SettingsViewModel
}