package uk.nightlines.core.common

import com.github.terrakok.modo.Screen

interface RootScreensInteractor {

    fun setStackScreen(count: Int): Screen

    fun commandScreen(counter: Int): Screen
}