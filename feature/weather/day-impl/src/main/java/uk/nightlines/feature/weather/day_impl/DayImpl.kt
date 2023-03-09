package uk.nightlines.feature.weather.day_impl

import android.util.Log
import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.day_api.DayApi
import uk.nightlines.feature.weather.day_impl.ui.DayScreen

class DayImpl : DayApi {
    override fun getDayScreen(): Screen {
        Log.d("GTA5", "get day screen")
        return DayScreen()
    }
}