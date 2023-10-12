package uk.nightlines.feature.complex.feature_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.complex.feature_api.ComplexFeatureApi
import uk.nightlines.feature.complex.feature_impl.ui.ComplexScreen

class ComplexFeatureImpl : ComplexFeatureApi {
    override fun screen(): Screen {
        return ComplexScreen()
    }
}