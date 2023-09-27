package uk.nightlines.feature.settings.impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.api.SettingsApi
import uk.nightlines.feature.settings.impl.ui.SettingsStack

class SettingsImpl : SettingsApi {
    override fun settingsScreen(): Screen {
        return SettingsStack()
    }
}