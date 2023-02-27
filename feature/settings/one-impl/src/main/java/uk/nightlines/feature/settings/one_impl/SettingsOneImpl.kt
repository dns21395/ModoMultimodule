package uk.nightlines.feature.settings.one_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.one_api.SettingsOneApi
import uk.nightlines.feature.settings.one_impl.ui.SettingsOneScreen

class SettingsOneImpl : SettingsOneApi {

    override fun getScreen(): Screen {
        return SettingsOneScreen()
    }
}
