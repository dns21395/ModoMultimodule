package uk.nightlines.feature.settings.two_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.NavigationTypeSetStackProvider
import uk.nightlines.feature.settings.common.SetStackDependencies
import uk.nightlines.feature.settings.common.SetStackNavigationQualifier
import uk.nightlines.feature.settings.one_api.OneScreenApi
import uk.nightlines.feature.settings.one_api.OneScreenApiProvider

@Module
class TwoModule {

    @FeatureScope
    @Provides
    fun provideTwoApi(setStackDependencies: SetStackDependencies): OneScreenApi =
        (setStackDependencies as OneScreenApiProvider).provideSettingsOneApi()

    @FeatureScope
    @Provides
    @SetStackNavigationQualifier
    fun provideNavigationTypeSetStack(setStackDependencies: SetStackDependencies): NavigationTypeSetStack =
        (setStackDependencies as NavigationTypeSetStackProvider).provideNavigationTypeSetStack()
}

