package uk.nightlines.feature.settings.main_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.feature.settings.one_api.SettingsOneApi
import uk.nightlines.feature.settings.one_impl.SettingsOneImpl
import uk.nightlines.feature.settings.two_api.SettingsTwoApi
import uk.nightlines.feature.settings.two_impl.SettingsTwoImpl

@Module
class SettingsModule {

    @PerFeature
    @Provides
    fun provideSettingsOneApi(): SettingsOneApi = SettingsOneImpl()

    @PerFeature
    @Provides
    fun provideSettingsTwoApi(): SettingsTwoApi = SettingsTwoImpl()

    @PerFeature
    @Provides
    fun provideNavigation() = Navigation()
}