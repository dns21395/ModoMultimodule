package uk.nightlines.feature.settings.two_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.two_api.SettingsTwoApi

class SettingsTwoImpl : SettingsTwoApi {

    override fun getSettingsTwoScreen(): Screen {
        return SettingsTwoScreen()
    }
}