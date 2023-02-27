package uk.nightlines.modomultimodule.di

import dagger.Component
import uk.nightlines.core.di.CoreProvider
import javax.inject.Singleton

@Component(
    modules = [
        AppModuule::class,
        ScreensModule::class
    ]
)
@Singleton
interface AppComponent : CoreProvider
