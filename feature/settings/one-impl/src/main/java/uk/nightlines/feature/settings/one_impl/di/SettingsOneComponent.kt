package uk.nightlines.feature.settings.one_impl.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.FeatureScope

@FeatureScope
@Component(
    dependencies = [
        CoreProvider::class,
    ]
)
interface SettingsOneComponent {

    @Component.Factory
    interface Builder {
        fun create(
            coreProvider: CoreProvider
        ): SettingsOneComponent
    }
}