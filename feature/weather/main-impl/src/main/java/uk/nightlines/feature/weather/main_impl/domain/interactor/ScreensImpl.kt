package uk.nightlines.feature.weather.main_impl.domain.interactor

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.weather.common.WeatherScreens
import uk.nightlines.feature.weather.day_api.DayApi
import uk.nightlines.feature.weather.week_api.WeekApi
import javax.inject.Inject

internal class ScreensImpl @Inject constructor(
    private val dayApi: DayApi,
    private val weekApi: WeekApi,
): WeatherScreens {
    override fun getDayScreen(): Screen = dayApi.getDayScreen()

    override fun getWeekScreen(): Screen = weekApi.getWeekScreen()
}