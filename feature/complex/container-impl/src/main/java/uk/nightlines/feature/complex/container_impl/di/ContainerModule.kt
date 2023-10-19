package uk.nightlines.feature.complex.container_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.di.FeatureScope
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.feature.complex.common.FeatureNavigationQualifier
import uk.nightlines.feature.complex.feature_api.ComplexFeatureApi
import uk.nightlines.feature.complex.feature_impl.ComplexFeatureImpl

@Module
internal class ContainerModule {

    @FeatureScope
    @Provides
    @FeatureNavigationQualifier
    fun provideComplexNavigation() = Navigation()

    @FeatureScope
    @Provides
    fun provideDayScreenApi(): ComplexFeatureApi = ComplexFeatureImpl()
}