package uk.nightlines.feature.weather.week_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.week_api.WeekApi

class WeekImpl : WeekApi {
    override fun getWeekScreen(): Screen = WeekScreen()
}