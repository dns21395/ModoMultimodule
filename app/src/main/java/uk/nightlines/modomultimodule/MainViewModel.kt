package uk.nightlines.modomultimodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.type.NavigationTypeCommand
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
    @RootNavigationQualifier private val navigation: NavigationTypeCommand,
    private val rootScreens: RootScreens
) : ViewModel() {

    init {
        viewModelScope.launch {
            navigation.navigate(NavigationReplace(rootScreens.weather(0)))
        }
    }

    val navigationCommands: Flow<NavigationCommand> = navigation.commandsFlow

}