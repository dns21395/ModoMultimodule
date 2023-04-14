package uk.nightlines.core.navigation.type

import android.util.Log
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import uk.nightlines.core.navigation.*

class NavigationTypeSetStack {

    private val state: ArrayList<Screen> = arrayListOf()

    private val _screensStackFlow = Channel<List<Screen>>()

    val screensStackFlow: Flow<List<Screen>> = _screensStackFlow.receiveAsFlow()

    suspend fun navigate(action: NavigationCommand) {
        Log.d("GTA5", "receive : $action")

        val newState: List<Screen> = when (action) {
            is NavigationSetStack -> {
                action.screens
            }
            is NavigationForward -> {
                state + listOf(action.screen, *action.screens.toTypedArray())
            }
            is NavigationReplace -> {
                state.dropLast(1) + listOf(action.screen, *action.screens.toTypedArray())
            }
            is NavigationNewStack -> {
                listOf(action.screen, *action.screens.toTypedArray())
            }
            is NavigationBackTo -> {
                val i = state.indexOfLast { it == action.screen }
                if (i != -1) state.take(i + 1)
                else state
            }
            is NavigationBackToRoot -> {
                listOfNotNull(state.firstOrNull())
            }
            is NavigationBack -> {
                state.dropLast(1)
            }
            is NavigationRemoveScreen -> {
                state.filterIndexed { i, _ -> i !in action.positions }
            }
            is NavigationExit -> {
                emptyList()
            }
            else -> {
                state
            }
        }

        state.clear()
        state.addAll(newState)

        _screensStackFlow.send(state)
    }
}