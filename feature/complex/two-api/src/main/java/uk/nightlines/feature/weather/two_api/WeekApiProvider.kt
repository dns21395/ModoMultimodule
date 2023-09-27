package uk.nightlines.feature.weather.two_api

import uk.nightlines.feature.weather.common.FeatureDependencies

interface WeekApiProvider : FeatureDependencies {

    fun weekScreenApi(): WeekScreenApi
}