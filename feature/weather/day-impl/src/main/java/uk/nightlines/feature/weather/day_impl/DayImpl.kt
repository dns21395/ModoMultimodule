package uk.nightlines.feature.weather.day_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.day_api.DayApi

class DayImpl : DayApi {
    override fun getDayScreen(): Screen {
        return DayScreen()
    }
}