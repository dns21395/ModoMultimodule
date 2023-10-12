package uk.nightlines.feature.complex.container_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.complex.container_api.ComplexApi
import uk.nightlines.feature.complex.container_impl.ui.ComplexStack

class ComplexImpl : ComplexApi {

    override fun screen(): Screen {
        return ComplexStack()
    }
}