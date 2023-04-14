package uk.nightlines.feature.settings.one_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.common.RootScreensCounterInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.type.NavigationTypeCommand
import uk.nightlines.core.navigation.type.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SettingsNavigationQualifier
import uk.nightlines.feature.settings.common.SettingsScreens
import javax.inject.Inject

internal class SettingsOneViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @SettingsNavigationQualifier private val settingsNavigation: NavigationTypeSetStack,
    private val settingsScreens: SettingsScreens,
    private val rootScreens: RootScreensInteractor,
    private val weatherScreenCounterInteractor: RootScreensCounterInteractor
) : ViewModel() {

    private val mutableState = MutableStateFlow(SettingsOneViewState())
    val state: StateFlow<SettingsOneViewState> = mutableState

    suspend fun onOpenSettingsTwoScreenClicked() {
        settingsNavigation.navigate(NavigationReplace(settingsScreens.twoScreen()))
    }

    suspend fun onForwardTwoButtonClicked() {
        settingsNavigation.navigate(NavigationForward(settingsScreens.twoScreen()))
    }

    suspend fun onOpenWeatherScreenClicked() {
        val weatherCounter = weatherScreenCounterInteractor.getWeatherScreenCount()

        rootNavigation.navigate(NavigationForward(rootScreens.weather(weatherCounter)))
    }

    suspend fun onTextChangedAction(text: String) {
        mutableState.emit(mutableState.value.copy(editText = text))
    }
}
