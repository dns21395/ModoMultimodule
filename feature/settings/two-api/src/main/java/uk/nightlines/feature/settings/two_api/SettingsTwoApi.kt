package uk.nightlines.feature.settings.two_api

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.common.SetStackDependencies

interface SettingsTwoApi : SetStackDependencies {

    fun getSettingsTwoScreen(): Screen
}
