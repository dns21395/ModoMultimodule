package uk.nightlines.feature.settings.two_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.common.state.SimpleState
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.core.navigation.setstack.NavigationTypeSetStack
import uk.nightlines.feature.settings.common.SetStackNavigationQualifier
import uk.nightlines.feature.settings.one_api.OneScreenApi
import javax.inject.Inject

internal class SettingsTwoViewModel @Inject constructor(
    @SetStackNavigationQualifier private val settingsNavigation: NavigationTypeSetStack,
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    private val oneScreenApi: OneScreenApi,
    private val rootScreens: RootScreensInteractor,
    ) : ViewModel() {

    private val mutableState = MutableStateFlow(SimpleState())
    val state: StateFlow<SimpleState> = mutableState

    suspend fun onForwardButtonClicked() {
        settingsNavigation.navigate(NavigationForward(oneScreenApi.getScreen()))
    }

    suspend fun onReplaceButtonClicked() {
        settingsNavigation.navigate(NavigationReplace(oneScreenApi.getScreen()))
    }

    suspend fun onOpenWeekButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.weatherScreen()))
    }
}