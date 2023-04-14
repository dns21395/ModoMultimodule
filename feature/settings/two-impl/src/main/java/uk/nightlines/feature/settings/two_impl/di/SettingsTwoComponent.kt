package uk.nightlines.feature.settings.two_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.settings.common.SetStackDependenciesApi
import uk.nightlines.feature.settings.two_impl.ui.SettingsTwoViewModel

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        SetStackDependenciesApi::class
    ]
)
internal interface SettingsTwoComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            settingsDependencies: SetStackDependenciesApi,
        ): SettingsTwoComponent
    }

    fun viewModel(): SettingsTwoViewModel
}