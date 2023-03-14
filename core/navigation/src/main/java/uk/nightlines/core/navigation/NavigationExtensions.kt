package uk.nightlines.core.navigation

import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.forward
import com.github.terrakok.modo.stack.replace

fun NavigationContainer<StackState>.navigate(command: NavigationCommand) {
    when (command) {
        is NavigationForward -> forward(command.screen, *command.screens.toTypedArray())
        is NavigationReplace -> replace(command.screen, *command.screens.toTypedArray())
    }
}