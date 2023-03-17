package uk.nightlines.feature.settings.one_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.WeatherScreenCounterInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsScreens
import javax.inject.Inject

internal class SettingsOneViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @SettingsNavigationQualifier private val settingsNavigation: Navigation,
    private val settingsScreens: SettingsScreens,
    private val rootScreens: RootScreens,
    private val weatherScreenCounterInteractor: WeatherScreenCounterInteractor
) : ViewModel() {

    private val mutableState = MutableStateFlow(SettingsOneViewState())
    val state: StateFlow<SettingsOneViewState> = mutableState

    suspend fun onOpenSettingsTwoScreenClicked() {
        settingsNavigation.navigate(NavigationReplace(settingsScreens.twoScreen()))
    }

    suspend fun onOpenWeatherScreenClicked() {
        val weatherCounter = weatherScreenCounterInteractor.getWeatherScreenCount()

        rootNavigation.navigate(NavigationForward(rootScreens.weather(weatherCounter)))
    }

    suspend fun onTextChangedAction(text: String) {
        mutableState.emit(mutableState.value.copy(editText = text))
    }
}
