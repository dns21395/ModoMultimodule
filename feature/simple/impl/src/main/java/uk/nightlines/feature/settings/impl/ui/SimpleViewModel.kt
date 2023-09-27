package uk.nightlines.feature.settings.impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import javax.inject.Inject

internal class SimpleViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    private val rootScreens: RootScreensInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow(SimpleViewState())
    val state: StateFlow<SimpleViewState> = _state

    suspend fun onForwardComplexButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.weatherScreen()))
    }

    suspend fun onReplaceComplexButtonClicked() {
        rootNavigation.navigate(NavigationReplace(rootScreens.weatherScreen()))
    }

    suspend fun onForwardSimpleButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.settingsScreen()))
    }

    suspend fun onReplaceSimpleButtonClicked() {
        rootNavigation.navigate(NavigationReplace(rootScreens.settingsScreen()))
    }
}