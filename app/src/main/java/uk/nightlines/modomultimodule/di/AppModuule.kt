package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.modomultimodule.RootScreenImpl
import javax.inject.Singleton

@Module
internal class AppModuule {

    @Provides
    @Singleton
    @RootNavigationQualifier
    fun provideNavigationTypeCommand(): NavigationTypeCommand = NavigationTypeCommand()

    @Provides
    @Singleton
    fun provideRootScreensInteractor(rootScreenImpl: RootScreenImpl): RootScreensInteractor = rootScreenImpl
}