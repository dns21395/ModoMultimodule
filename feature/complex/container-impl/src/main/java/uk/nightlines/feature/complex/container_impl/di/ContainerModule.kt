package uk.nightlines.feature.complex.container_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.complex.feature_api.ComplexFeatureApi
import uk.nightlines.feature.complex.feature_impl.ComplexFeatureImpl
import uk.nightlines.feature.complex.common.FeatureNavigationQualifier

@Module
internal class ContainerModule {

    @PerFeature
    @Provides
    @FeatureNavigationQualifier
    fun provideNavigation() = NavigationTypeCommand()

    @PerFeature
    @Provides
    fun provideDayScreenApi(): ComplexFeatureApi = ComplexFeatureImpl()
}