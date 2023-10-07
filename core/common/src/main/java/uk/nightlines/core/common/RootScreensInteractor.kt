package uk.nightlines.core.common

import com.github.terrakok.modo.Screen

interface RootScreensInteractor {

    fun simpleScreen(): Screen

    fun complexScreen(): Screen
}