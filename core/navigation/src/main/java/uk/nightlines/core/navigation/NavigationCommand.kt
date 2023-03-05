package uk.nightlines.core.navigation

import com.github.terrakok.modo.Screen

interface NavigationCommand

data class NavigationForward(val screen: Screen, val screens: List<Screen> = emptyList()) : NavigationCommand

data class NavigationReplace(val screen: Screen, val screens: List<Screen> = emptyList()) : NavigationCommand

object NavigationBack : NavigationCommand
