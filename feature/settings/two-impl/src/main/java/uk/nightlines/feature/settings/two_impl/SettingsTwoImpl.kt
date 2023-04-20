package uk.nightlines.feature.settings.two_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.two_api.TwoScreenApi
import uk.nightlines.feature.settings.two_impl.ui.SettingsTwoScreen

class SettingsTwoImpl : TwoScreenApi {

    override fun getScreen(): Screen {
        return SettingsTwoScreen()
    }
}