package uk.nightlines.feature.settings.one_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.feature.settings.main_api.SettingsDependencies
import javax.inject.Named

@Module
class SettingsOneModule {

    @PerFeature
    @Provides
    @Named("settings")
    fun provideNavigation(settingsDependencies: SettingsDependencies): Navigation = settingsDependencies.getNavigation()
}