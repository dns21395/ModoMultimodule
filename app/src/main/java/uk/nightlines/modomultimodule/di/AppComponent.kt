package uk.nightlines.modomultimodule.di

import dagger.Component
import uk.nightlines.core.di.di.CoreProvider
import uk.nightlines.modomultimodule.MainViewModel
import javax.inject.Singleton

@Component(
    modules = [
        AppModuule::class,
    ]
)
@Singleton
internal interface AppComponent : CoreProvider {
    fun viewModel(): MainViewModel
}
