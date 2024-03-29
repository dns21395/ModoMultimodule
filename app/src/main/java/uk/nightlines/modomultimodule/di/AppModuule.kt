package uk.nightlines.modomultimodule.di

import dagger.Module
import dagger.Provides
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.feature.simple.api.SimpleApi
import uk.nightlines.feature.simple.impl.SimpleImpl
import uk.nightlines.feature.complex.container_api.ComplexApi
import uk.nightlines.feature.complex.container_impl.ComplexImpl
import uk.nightlines.modomultimodule.navigation.RootScreenImpl
import javax.inject.Singleton

@Module
internal class AppModuule {

    @Provides
    @Singleton
    @RootNavigationQualifier
    fun provideRootNavigation(): Navigation = Navigation()

    @Provides
    @Singleton
    fun provideRootScreensInteractor(impl: RootScreenImpl): RootScreensInteractor = impl

    @Provides
    @Singleton
    fun provideComplexApi(): ComplexApi = ComplexImpl()

    @Provides
    @Singleton
    fun provideSimpleApi(): SimpleApi = SimpleImpl()
}