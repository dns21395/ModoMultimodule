package uk.nightlines.modomultimodule.navigation

import com.github.terrakok.modo.Screen
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.feature.simple.api.SimpleApi
import uk.nightlines.feature.complex.container_api.ComplexApi
import javax.inject.Inject

class RootScreenImpl @Inject constructor(
    private val simpleApi: SimpleApi,
    private val complexApi: ComplexApi
) : RootScreensInteractor {

    override fun complexScreen(): Screen = complexApi.screen()

    override fun simpleScreen(): Screen = simpleApi.screen()
}