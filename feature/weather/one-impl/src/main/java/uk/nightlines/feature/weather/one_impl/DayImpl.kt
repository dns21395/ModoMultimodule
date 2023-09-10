package uk.nightlines.feature.weather.one_impl

import android.util.Log
import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.one_api.DayScreenApi
import uk.nightlines.feature.weather.one_impl.ui.DayScreen

class DayImpl : DayScreenApi {
    override fun getDayScreen(): Screen {
        Log.d("GTA5", "get day screen")
        return DayScreen()
    }
}