package uk.nightlines.modomultimodule.di

import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModuule::class
    ]
)
@Singleton
interface AppComponent : NavigationProvider
