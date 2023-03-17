package uk.nightlines.core.navigation

import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.stack.*

fun NavigationContainer<StackState>.navigate(command: NavigationCommand) {
    when (command) {
        is NavigationNewStack -> setStack(StackState(stack = command.screens))
        is NavigationForward -> forward(command.screen, *command.screens.toTypedArray())
        is NavigationReplace -> replace(command.screen, *command.screens.toTypedArray())
        is NavigationRemoveScreen -> removeScreen(*command.positions.toIntArray())
        is NavigationBackToRoot -> backToRoot()
        is NavigationExit -> exit()
    }
}