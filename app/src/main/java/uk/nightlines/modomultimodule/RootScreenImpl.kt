package uk.nightlines.modomultimodule

import com.github.terrakok.modo.Screen
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.feature.settings.main_api.SettingsApi
import uk.nightlines.feature.weather.main_api.WeatherApi
import javax.inject.Inject

class RootScreenImpl @Inject constructor(
    private val settingsApi: SettingsApi,
    private val weatherApi: WeatherApi
) : RootScreensInteractor {

    override fun weatherScreen(): Screen = weatherApi.weatherScreen()

    override fun settingsScreen(): Screen = settingsApi.settingsScreen()
}