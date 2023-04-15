package uk.nightlines.feature.weather.week_api

import uk.nightlines.feature.weather.common.WeatherDependencies

interface WeekApiProvider : WeatherDependencies {

    fun weekScreenApi(): WeekScreenApi
}