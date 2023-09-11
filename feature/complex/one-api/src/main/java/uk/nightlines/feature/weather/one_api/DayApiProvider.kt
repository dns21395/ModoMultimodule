package uk.nightlines.feature.weather.one_api

import uk.nightlines.feature.weather.common.WeatherDependencies

interface DayApiProvider : WeatherDependencies {

    fun dayApi(): DayScreenApi
}