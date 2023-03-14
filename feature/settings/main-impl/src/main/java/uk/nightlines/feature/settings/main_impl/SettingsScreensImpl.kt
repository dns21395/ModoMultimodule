package uk.nightlines.feature.settings.main_impl

import com.github.terrakok.modo.Screen
import uk.nightlines.feature.settings.common.SettingsScreens
import uk.nightlines.feature.settings.one_api.SettingsOneApi
import uk.nightlines.feature.settings.two_api.SettingsTwoApi
import javax.inject.Inject

internal class SettingsScreensImpl @Inject constructor(
    private val oneApi: SettingsOneApi,
    private val twoApi: SettingsTwoApi
): SettingsScreens {

    override fun oneScreen(): Screen = oneApi.getScreen()

    override fun twoScreen(): Screen = twoApi.getSettingsTwoScreen()
}