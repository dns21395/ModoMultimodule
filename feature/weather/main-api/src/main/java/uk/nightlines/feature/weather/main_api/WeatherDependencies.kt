package uk.nightlines.feature.weather.main_api

import uk.nightlines.core.navigation.Navigation
import uk.nightlines.feature.weather.day_api.DayApi
import uk.nightlines.feature.weather.week_api.WeekApi

interface WeatherDependencies {

    @WeatherNavigationQualifier
    fun getWeatherNavigation(): Navigation

    fun getDayApi(): DayApi

    fun getWeekApi(): WeekApi
}