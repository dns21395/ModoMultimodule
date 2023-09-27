package uk.nightlines.feature.weather.one_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.FeatureDependencies
import uk.nightlines.feature.weather.common.FeatureNavigationProvider
import uk.nightlines.feature.weather.common.FeatureNavigationQualifier
import uk.nightlines.feature.weather.two_api.WeekApiProvider
import uk.nightlines.feature.weather.two_api.WeekScreenApi

@Module
internal class DayModule {

    @FeatureScope
    @Provides
    @FeatureNavigationQualifier
    fun provideNavigation(featureDependencies: FeatureDependencies): NavigationTypeCommand {
        return (featureDependencies as FeatureNavigationProvider).provideNavigation()
    }

    @FeatureScope
    @Provides
    fun provideWeekScreenApi(featureDependencies: FeatureDependencies): WeekScreenApi {
        return (featureDependencies as WeekApiProvider).weekScreenApi()
    }
}