package uk.nightlines.core.common

interface WeatherScreenCounterInteractor {

    fun incrementScreenCount()

    fun getWeatherScreenCount(): Int

    fun getSettingsScreenCount(): Int
}