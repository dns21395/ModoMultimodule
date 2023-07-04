package uk.nightlines.feature.settings.one_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.common.state.SimpleEditTextState
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SetStackNavigationQualifier
import uk.nightlines.feature.settings.two_api.TwoScreenApi
import javax.inject.Inject

internal class SettingsOneViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @SetStackNavigationQualifier private val settingsNavigation: NavigationTypeSetStack,
    private val twoApi: TwoScreenApi,
    private val rootScreens: RootScreensInteractor,
) : ViewModel() {

    private val mutableState = MutableStateFlow(SimpleEditTextState())
    val state: StateFlow<SimpleEditTextState> = mutableState

    suspend fun onReplaceButtonClicked() {
        settingsNavigation.navigate(NavigationReplace(twoApi.getScreen()))
    }

    suspend fun onForwardButtonClicked() {
        settingsNavigation.navigate(NavigationForward(twoApi.getScreen()))
    }

    suspend fun onOpenWeatherScreenClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.weatherScreen()))
    }

    suspend fun onTextChangedAction(text: String) {
        mutableState.emit(mutableState.value.copy(editText = text))
    }
}
