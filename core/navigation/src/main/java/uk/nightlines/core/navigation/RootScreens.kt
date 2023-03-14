package uk.nightlines.core.navigation

import com.github.terrakok.modo.Screen

interface RootScreens {

    fun settings(counter: Int): Screen

    fun weather(counter: Int): Screen
}