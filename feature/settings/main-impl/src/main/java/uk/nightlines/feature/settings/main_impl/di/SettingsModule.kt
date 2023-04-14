package uk.nightlines.feature.settings.main_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.type.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsScreens
import uk.nightlines.feature.settings.main_impl.SettingsScreensImpl
import uk.nightlines.feature.settings.one_api.SettingsOneApi
import uk.nightlines.feature.settings.one_impl.SettingsOneImpl
import uk.nightlines.feature.settings.two_api.SettingsTwoApi
import uk.nightlines.feature.settings.two_impl.SettingsTwoImpl

@Module
internal class SettingsModule {

    @PerFeature
    @Provides
    fun provideSettingsOneApi(): SettingsOneApi = SettingsOneImpl()

    @PerFeature
    @Provides
    fun provideSettingsTwoApi(): SettingsTwoApi = SettingsTwoImpl()

    @PerFeature
    @Provides
    @SettingsNavigationQualifier
    fun provideNavigationStackList(): NavigationTypeSetStack = NavigationTypeSetStack()

    @PerFeature
    @Provides
    fun provideSettingsScreens(settingsScreensImpl: SettingsScreensImpl): SettingsScreens =
        settingsScreensImpl
}