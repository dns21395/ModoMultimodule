package uk.nightlines.feature.weather.container_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.container_api.ComplexApi
import uk.nightlines.feature.weather.container_impl.ui.ComplexStack

class ComplexImpl : ComplexApi {

    override fun screen(): Screen {
        return ComplexStack()
    }
}