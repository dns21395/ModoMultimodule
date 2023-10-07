package uk.nightlines.feature.weather.one_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.one_api.ComplexFeatureApi
import uk.nightlines.feature.weather.one_impl.ui.ComplexScreen

class ComplexFeatureImpl : ComplexFeatureApi {
    override fun screen(): Screen {
        return ComplexScreen()
    }
}