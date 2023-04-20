package uk.nightlines.core.navigation.command

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import uk.nightlines.core.navigation.NavigationCommand

class NavigationTypeCommand {

    private val _commandsFlow = Channel<NavigationCommand>()

    val commandsFlow: Flow<NavigationCommand> = _commandsFlow.receiveAsFlow()

    suspend fun navigate(command: NavigationCommand) {
        _commandsFlow.send(command)
    }
}