package uk.nightlines.feature.settings.main_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.main_api.SettingsApi
import uk.nightlines.feature.settings.main_impl.ui.SettingsStack

class SettingsImpl : SettingsApi {
    override fun settingsScreen(): Screen {
        return SettingsStack()
    }
}