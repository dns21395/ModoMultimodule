package uk.nightlines.modomultimodule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.NavigationBack
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.core.navigation.RootNavigationQualifier
import javax.inject.Inject

internal class MainViewModel @Inject constructor(
    @RootNavigationQualifier private val navigation: Navigation,
) : ViewModel() {

    val navigationCommands: SharedFlow<NavigationCommand> = navigation.commandsFlow

    suspend fun onBackButtonPressed() {
        navigation.navigate(NavigationBack)
    }

}