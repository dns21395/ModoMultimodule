package uk.nightlines.modomultimodule.domain.interactor

import uk.nightlines.core.common.WeatherScreenCounterInteractor

internal class WeatherScreenCounterInteractorImpl : WeatherScreenCounterInteractor {

    private var counter = 0

    override fun incrementScreenCount() {
        counter++
    }

    override fun getScreenCount(): Int = counter
}
