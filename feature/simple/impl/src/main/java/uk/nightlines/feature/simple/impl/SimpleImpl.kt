package uk.nightlines.feature.simple.impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.simple.api.SimpleApi
import uk.nightlines.feature.simple.impl.ui.SimpleStack

class SimpleImpl : SimpleApi {
    override fun screen(): Screen {
        return SimpleStack()
    }
}