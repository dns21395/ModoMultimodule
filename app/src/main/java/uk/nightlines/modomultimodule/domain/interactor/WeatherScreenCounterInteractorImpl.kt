package uk.nightlines.modomultimodule.domain.interactor

import uk.nightlines.core.common.RootScreensCounterInteractor

internal class WeatherScreenCounterInteractorImpl : RootScreensCounterInteractor {

    private var settingsCounter = 0
    private var weatherCounter = 1

    override fun incrementScreenCount() {
        weatherCounter++
    }

    override fun getWeatherScreenCount(): Int = weatherCounter++

    override fun getSettingsScreenCount(): Int = settingsCounter++
}
