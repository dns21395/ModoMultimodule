package uk.nightlines.feature.settings.two_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.feature.settings.main_api.SettingsDependencies

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
        SettingsDependencies::class
    ]
)
internal interface SettingsTwoComponent : CoreProvider, SettingsDependencies {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider,
            settingsDependencies: SettingsDependencies
        ): SettingsTwoComponent
    }
}