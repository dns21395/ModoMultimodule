package uk.nightlines.feature.settings.main_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SetStackNavigationQualifier
import uk.nightlines.feature.settings.one_api.OneScreenApi
import uk.nightlines.feature.settings.one_impl.SettingsOneImpl
import uk.nightlines.feature.settings.two_api.TwoScreenApi
import uk.nightlines.feature.settings.two_impl.SettingsTwoImpl

@Module
internal class SettingsModule {

    @PerFeature
    @Provides
    fun provideSettingsOneApi(): OneScreenApi = SettingsOneImpl()

    @PerFeature
    @Provides
    fun provideSettingsTwoApi(): TwoScreenApi = SettingsTwoImpl()

    @PerFeature
    @Provides
    @SetStackNavigationQualifier
    fun provideNavigationStackList(): NavigationTypeSetStack = NavigationTypeSetStack()
}