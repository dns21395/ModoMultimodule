package uk.nightlines.feature.weather.two_impl.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.di.FeatureScope
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.FeatureDependencies
import uk.nightlines.feature.weather.common.FeatureNavigationProvider
import uk.nightlines.feature.weather.common.FeatureNavigationQualifier
import uk.nightlines.feature.weather.one_api.DayScreenApi
import uk.nightlines.feature.weather.one_api.DayApiProvider

@Module
internal class WeekModule {

    @FeatureScope
    @Provides
    @FeatureNavigationQualifier
    fun provideNavigationTypeCommand(featureDependencies: FeatureDependencies): NavigationTypeCommand {
        return (featureDependencies as FeatureNavigationProvider).provideNavigation()
    }

    @FeatureScope
    @Provides
    fun provideDayApi(featureDependencies: FeatureDependencies): DayScreenApi =
        (featureDependencies as DayApiProvider).dayApi()
}
