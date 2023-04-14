package uk.nightlines.core.common

import com.github.terrakok.modo.Screen

interface RootScreensInteractor {

    fun settings(count: Int): Screen

    fun weather(counter: Int): Screen
}