package uk.nightlines.feature.weather.one_api

import uk.nightlines.feature.weather.common.FeatureDependencies

interface ScreenApiProvider : FeatureDependencies {

    fun dayApi(): ScreenApi
}