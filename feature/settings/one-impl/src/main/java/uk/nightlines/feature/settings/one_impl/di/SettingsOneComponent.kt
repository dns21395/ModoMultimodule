package uk.nightlines.feature.settings.one_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.settings.common.SettingsDependencies
import uk.nightlines.feature.settings.one_impl.ui.SettingsOneViewModel

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        SettingsDependencies::class
    ]
)
internal interface SettingsOneComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            settingsDependencies: SettingsDependencies
        ): SettingsOneComponent
    }

    fun viewModel(): SettingsOneViewModel
}