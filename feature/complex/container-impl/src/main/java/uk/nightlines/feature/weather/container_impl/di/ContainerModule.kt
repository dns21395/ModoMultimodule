package uk.nightlines.feature.weather.container_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.PerFeature
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.complex.feature_api.ComplexFeatureApi
import uk.nightlines.feature.complex.feature_impl.ComplexFeatureImpl
import uk.nightlines.feature.weather.common.FeatureNavigationQualifier

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