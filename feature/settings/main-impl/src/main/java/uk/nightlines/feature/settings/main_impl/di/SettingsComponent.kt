package uk.nightlines.feature.settings.main_impl.di

import dagger.Component
import uk.nightlines.core.di.PerFeature

@Component(
    modules = [SettingsModule::class]
)
@PerFeature
interface SettingsComponent {
}