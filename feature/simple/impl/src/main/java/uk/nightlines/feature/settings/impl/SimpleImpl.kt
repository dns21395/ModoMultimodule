package uk.nightlines.feature.settings.impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.api.SimpleApi
import uk.nightlines.feature.settings.impl.ui.SettingsStack

class SimpleImpl : SimpleApi {
    override fun screen(): Screen {
        return SettingsStack()
    }
}