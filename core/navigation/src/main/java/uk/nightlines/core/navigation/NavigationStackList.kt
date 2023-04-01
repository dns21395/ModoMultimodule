package uk.nightlines.core.navigation

import com.github.terrakok.modo.NavigationAction
import com.github.terrakok.modo.stack.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class NavigationStackList {

    private val _screensStackFlow = Channel<StackState>()

    val screensStackFlow: Flow<StackState> = _screensStackFlow.receiveAsFlow()

    suspend fun navigate(action: NavigationAction) {
        val state = _screensStackFlow.receive()

        val newState = when (action) {
            is SetStack -> action.state
            is Forward -> StackState(
                state.stack + listOf(action.screen, *action.screens)
            )
            is Replace -> StackState(
                state.stack.dropLast(1) + listOf(action.screen, *action.screens)
            )
            is NewStack -> StackState(
                listOf(action.screen, *action.screens)
            )
            is BackTo -> {
                val i = state.stack.indexOfLast { it == action.screen }
                if (i != -1) StackState(state.stack.take(i + 1))
                else state
            }
            is BackToRoot -> StackState(
                listOfNotNull(state.stack.firstOrNull())
            )
            is Back -> StackState(
                state.stack.dropLast(1)
            )
            is RemoveScreens -> StackState(
                state.stack.filterIndexed { i, _ -> i !in action.positions }
            )
            is Exit -> StackState()
            else -> state
        }

        _screensStackFlow.send(newState)
    }
}