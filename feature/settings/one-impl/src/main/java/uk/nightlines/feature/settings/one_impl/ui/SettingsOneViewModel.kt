package uk.nightlines.feature.settings.one_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.common.RootScreensCounterInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.setstack.NavigationForward
import uk.nightlines.core.navigation.setstack.NavigationReplace
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SetStackNavigationQualifier
import uk.nightlines.feature.settings.two_api.SettingsTwoApi
import javax.inject.Inject

internal class SettingsOneViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @SetStackNavigationQualifier private val settingsNavigation: NavigationTypeSetStack,
    private val twoApi: SettingsTwoApi,
    private val rootScreens: RootScreensInteractor,
    private val weatherScreenCounterInteractor: RootScreensCounterInteractor
) : ViewModel() {

    private val mutableState = MutableStateFlow(SettingsOneViewState())
    val state: StateFlow<SettingsOneViewState> = mutableState

    suspend fun onOpenSettingsTwoScreenClicked() {
        settingsNavigation.navigate(NavigationReplace(twoApi.getSettingsTwoScreen()))
    }

    suspend fun onForwardTwoButtonClicked() {
        settingsNavigation.navigate(NavigationForward(twoApi.getSettingsTwoScreen()))
    }

    suspend fun onOpenWeatherScreenClicked() {
        val weatherCounter = weatherScreenCounterInteractor.getCommandScreenCount()

        rootNavigation.navigate(NavigationForward(rootScreens.commandScreen(weatherCounter)))
    }

    suspend fun onTextChangedAction(text: String) {
        mutableState.emit(mutableState.value.copy(editText = text))
    }
}
