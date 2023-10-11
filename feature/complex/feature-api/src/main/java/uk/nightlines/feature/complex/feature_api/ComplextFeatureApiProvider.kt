package uk.nightlines.feature.complex.feature_api

import uk.nightlines.feature.complex.common.FeatureDependencies

interface ComplextFeatureApiProvider : FeatureDependencies {

    fun complexFeatureApi(): ComplexFeatureApi
}