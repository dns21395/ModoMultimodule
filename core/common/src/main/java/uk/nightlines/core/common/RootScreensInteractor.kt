package uk.nightlines.core.common

import com.github.terrakok.modo.Screen

interface RootScreensInteractor {

    fun settingsScreen(): Screen

    fun weatherScreen(): Screen
}