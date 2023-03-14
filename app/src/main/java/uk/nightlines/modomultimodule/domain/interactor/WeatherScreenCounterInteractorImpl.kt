package uk.nightlines.modomultimodule.domain.interactor

import uk.nightlines.core.common.WeatherScreenCounterInteractor

internal class WeatherScreenCounterInteractorImpl : WeatherScreenCounterInteractor {

    private var settingsCounter = 0
    private var weatherCounter = 1

    override fun incrementScreenCount() {
        weatherCounter++
    }

    override fun getWeatherScreenCount(): Int = weatherCounter++

    override fun getSettingsScreenCount(): Int = settingsCounter++
}
