package uk.nightlines.feature.settings.main_impl.di

import dagger.Component
import uk.nightlines.core.di.PerFeature
import uk.nightlines.feature.settings.main_api.SettingsDependencies

@Component(
    modules = [SettingsModule::class]
)
@PerFeature
interface SettingsComponent : SettingsDependencies {
}