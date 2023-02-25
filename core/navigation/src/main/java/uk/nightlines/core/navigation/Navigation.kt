package uk.nightlines.core.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

class Navigation {

    val commandsFlow = MutableSharedFlow<NavigationCommand>()

    suspend fun navigate(command: NavigationCommand) {
        commandsFlow.emit(command)
    }
}