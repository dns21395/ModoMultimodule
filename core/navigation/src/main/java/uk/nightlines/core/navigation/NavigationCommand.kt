package uk.nightlines.core.navigation

import com.github.terrakok.modo.Screen

interface NavigationCommand

data class NavigationForward(val screen: Screen, val screens: List<Screen>) : NavigationCommand

data class NavigationReplace(val screen: Screen, val screens: List<Screen>) : NavigationCommand

object NavigationBack : NavigationCommand
