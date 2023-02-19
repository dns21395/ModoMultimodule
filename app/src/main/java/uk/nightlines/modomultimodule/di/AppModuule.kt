package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.modomultimodule.navigation.Navigation
import javax.inject.Singleton

@Module
class AppModuule {

    @Provides
    @Singleton
    fun provideNavigation(): Navigation = Navigation()
}