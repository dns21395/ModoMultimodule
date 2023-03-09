package uk.nightlines.core.navigation

import com.github.terrakok.modo.Screen

interface RootScreens {

    fun settings(): Screen

    fun weather(counter: Int): Screen
}