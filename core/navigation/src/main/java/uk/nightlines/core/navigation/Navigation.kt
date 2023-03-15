package uk.nightlines.core.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow

class Navigation {

    val channel = Channel<NavigationCommand>()

    val commandsFlow = MutableSharedFlow<NavigationCommand>()

    suspend fun navigate(command: NavigationCommand) {
        commandsFlow.emit(command)
    }

    suspend fun navigateNew(command: NavigationCommand) {
        commandsFlow.emit(command)
        channel.send(command)
    }
}