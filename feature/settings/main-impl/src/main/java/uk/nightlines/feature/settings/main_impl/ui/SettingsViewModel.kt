package uk.nightlines.feature.settings.main_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    private val rootScreens: RootScreensInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsViewState())
    val state: StateFlow<SettingsViewState> = _state

    suspend fun onForwardWeatherButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.weatherScreen()))
    }

    suspend fun onReplaceWeatherButtonClicked() {
        rootNavigation.navigate(NavigationReplace(rootScreens.weatherScreen()))
    }

    suspend fun onForwardSettingsButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.settingsScreen()))
    }

    suspend fun onReplaceSettingsButtonClicked() {
        rootNavigation.navigate(NavigationReplace(rootScreens.settingsScreen()))
    }
}