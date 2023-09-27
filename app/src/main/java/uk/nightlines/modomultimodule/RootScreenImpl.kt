package uk.nightlines.modomultimodule

import com.github.terrakok.modo.Screen
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.feature.settings.api.SimpleApi
import uk.nightlines.feature.weather.container_api.ComplexApi
import javax.inject.Inject

class RootScreenImpl @Inject constructor(
    private val simpleApi: SimpleApi,
    private val weatherApi: ComplexApi
) : RootScreensInteractor {

    override fun weatherScreen(): Screen = weatherApi.screen()

    override fun settingsScreen(): Screen = simpleApi.screen()
}