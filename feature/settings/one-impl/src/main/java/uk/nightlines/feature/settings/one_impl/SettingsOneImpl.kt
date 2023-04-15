package uk.nightlines.feature.settings.one_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.one_api.OneScreenApi
import uk.nightlines.feature.settings.one_impl.ui.SettingsOneScreen

class SettingsOneImpl : OneScreenApi {

    override fun getScreen(): Screen {
        return SettingsOneScreen()
    }
}
