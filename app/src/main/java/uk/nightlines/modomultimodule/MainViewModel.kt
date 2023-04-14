package uk.nightlines.modomultimodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.type.NavigationTypeCommand
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
    @RootNavigationQualifier private val navigation: NavigationTypeCommand,
    private val rootScreensInteractor: RootScreensInteractor
) : ViewModel() {

    init {
        viewModelScope.launch {
            navigation.navigate(NavigationReplace(rootScreensInteractor.commandScreen(0)))
        }
    }

    val navigationCommands: Flow<NavigationCommand> = navigation.commandsFlow

}