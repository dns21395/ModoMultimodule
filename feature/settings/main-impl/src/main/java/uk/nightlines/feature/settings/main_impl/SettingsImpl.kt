package uk.nightlines.feature.settings.main_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.main_api.SettingsApi

class SettingsImpl : SettingsApi {

    override fun getSetStackScreen(count: Int): Screen {
        return SettingsStack(count)
    }
}