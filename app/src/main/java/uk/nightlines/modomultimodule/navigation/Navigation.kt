package uk.nightlines.modomultimodule.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

class Navigation {
    val commandsFlow = MutableSharedFlow<NavigationCommand>()
}