package uk.nightlines.modomultimodule.navigation

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Singleton

class Navigation {

    init {
        Log.d("GTA5", "navigation init : ${this.hashCode()}")
    }

    val commandsFlow = MutableSharedFlow<NavigationCommand>()

    fun navigate(command: NavigationCommand) {
        GlobalScope.launch(Dispatchers.Main) {
            commandsFlow.emit(command)
        }
    }
}