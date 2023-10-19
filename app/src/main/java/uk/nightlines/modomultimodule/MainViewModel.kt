package uk.nightlines.modomultimodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.Navigation
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    private val rootScreensInteractor: RootScreensInteractor
) : ViewModel() {

    init {
        viewModelScope.launch {
            rootNavigation.navigate(NavigationReplace(rootScreensInteractor.complexScreen()))
        }
    }

    val navigationCommands: Flow<NavigationCommand> = rootNavigation.commandsFlow
}