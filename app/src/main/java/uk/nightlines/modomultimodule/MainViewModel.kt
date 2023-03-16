package uk.nightlines.modomultimodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.navigation.*
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
    @RootNavigationQualifier private val navigation: Navigation,
    private val rootScreens: RootScreens
) : ViewModel() {

    init {
        viewModelScope.launch {
            navigation.navigate(NavigationReplace(rootScreens.weather(0)))
        }
    }

    val navigationCommands: Flow<NavigationCommand> = navigation.commandsFlow

}