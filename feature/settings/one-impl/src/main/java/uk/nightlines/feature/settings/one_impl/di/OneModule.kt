package uk.nightlines.feature.settings.one_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.NavigationTypeSetStackProvider
import uk.nightlines.feature.settings.common.SetStackDependencies
import uk.nightlines.feature.settings.common.SetStackNavigationQualifier
import uk.nightlines.feature.settings.two_api.TwoScreenApi
import uk.nightlines.feature.settings.two_api.TwoScreenApiProvider

@Module
class OneModule {

    @FeatureScope
    @Provides
    fun provideTwoApi(setStackDependencies: SetStackDependencies): TwoScreenApi =
        (setStackDependencies as TwoScreenApiProvider).twoApi()

    @FeatureScope
    @Provides
    @SetStackNavigationQualifier
    fun provideNavigationTypeSetStack(setStackDependencies: SetStackDependencies): NavigationTypeSetStack =
        (setStackDependencies as NavigationTypeSetStackProvider).provideNavigationTypeSetStack()
}