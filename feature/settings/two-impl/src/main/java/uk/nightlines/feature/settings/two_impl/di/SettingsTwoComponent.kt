package uk.nightlines.feature.settings.two_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.settings.common.SetStackDependencies
import uk.nightlines.feature.settings.two_impl.ui.SettingsTwoViewModel

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        SetStackDependencies::class
    ],
    modules = [TwoModule::class]
)
internal interface SettingsTwoComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            settingsDependencies: SetStackDependencies,
        ): SettingsTwoComponent
    }

    fun viewModel(): SettingsTwoViewModel
}