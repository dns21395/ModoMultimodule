package uk.nightlines.feature.weather.two_api

import uk.nightlines.feature.weather.common.WeatherDependencies

interface WeekApiProvider : WeatherDependencies {

    fun weekScreenApi(): WeekScreenApi
}