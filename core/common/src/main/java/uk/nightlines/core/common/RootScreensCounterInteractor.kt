package uk.nightlines.core.common

interface RootScreensCounterInteractor {

    fun incrementScreenCount()

    fun getWeatherScreenCount(): Int

    fun getSettingsScreenCount(): Int
}