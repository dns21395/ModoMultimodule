package uk.nightlines.feature.weather.two_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.two_api.WeekScreenApi
import uk.nightlines.feature.weather.two_impl.ui.WeekScreen

class WeekImpl : WeekScreenApi {
    override fun getWeekScreen(): Screen = WeekScreen()
}