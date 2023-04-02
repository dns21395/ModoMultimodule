package uk.nightlines.core.navigation

import com.github.terrakok.modo.NavigationAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class NavigationStackList {

    private val _screensStackFlow = Channel<NavigationAction>()

    val screensStackFlow: Flow<NavigationAction> = _screensStackFlow.receiveAsFlow()

    suspend fun navigate(action: NavigationAction) {
        _screensStackFlow.send(action)
    }
}