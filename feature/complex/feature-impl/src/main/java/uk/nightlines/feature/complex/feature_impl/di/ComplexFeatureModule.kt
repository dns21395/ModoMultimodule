package uk.nightlines.feature.complex.feature_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.di.FeatureScope
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.complex.common.FeatureDependencies
import uk.nightlines.feature.complex.common.FeatureNavigationProvider
import uk.nightlines.feature.complex.common.FeatureNavigationQualifier

@Module
internal class ComplexFeatureModule {

    @FeatureScope
    @Provides
    @FeatureNavigationQualifier
    fun provideNavigation(featureDependencies: FeatureDependencies): NavigationTypeCommand {
        return (featureDependencies as FeatureNavigationProvider).provideNavigation()
    }
}