package uk.nightlines.feature.weather.one_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.one_api.ScreenApi
import uk.nightlines.feature.weather.one_impl.ui.DayScreen

class ScreenImpl : ScreenApi {
    override fun screen(): Screen {
        return DayScreen()
    }
}