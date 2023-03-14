package uk.nightlines.modomultimodule

import com.github.terrakok.modo.Screen
import uk.nightlines.core.navigation.RootScreens
import uk.nightlines.feature.settings.main_api.SettingsApi
import uk.nightlines.feature.weather.main_api.WeatherApi
import javax.inject.Inject

class RootScreenImpl @Inject constructor(
    private val settingsApi: SettingsApi,
    private val weatherApi: WeatherApi
) : RootScreens {

    override fun settings(count: Int): Screen = settingsApi.getSettingsScreen(count)

    override fun weather(counter: Int): Screen = weatherApi.getWeatherScreen(counter)
}