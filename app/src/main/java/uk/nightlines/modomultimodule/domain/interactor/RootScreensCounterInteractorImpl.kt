package uk.nightlines.modomultimodule.domain.interactor

import uk.nightlines.core.common.RootScreensCounterInteractor

internal class RootScreensCounterInteractorImpl : RootScreensCounterInteractor {

    private var settingsCounter = 1
    private var weatherCounter = 1

    override fun getCommandScreenCount(): Int = weatherCounter++

    override fun getSetStackScreenCount(): Int = settingsCounter++
}
