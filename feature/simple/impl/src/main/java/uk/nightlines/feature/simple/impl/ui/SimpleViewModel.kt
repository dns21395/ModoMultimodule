package uk.nightlines.feature.simple.impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.Navigation
import javax.inject.Inject

internal class SimpleViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    private val rootScreens: RootScreensInteractor,
) : ViewModel() {

    private val _state = MutableStateFlow(SimpleViewState())
    val state: StateFlow<SimpleViewState> = _state

    suspend fun onForwardComplexButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.complexScreen()))
    }

    suspend fun onReplaceComplexButtonClicked() {
        rootNavigation.navigate(NavigationReplace(rootScreens.complexScreen()))
    }

    suspend fun onForwardSimpleButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.simpleScreen()))
    }

    suspend fun onReplaceSimpleButtonClicked() {
        rootNavigation.navigate(NavigationReplace(rootScreens.simpleScreen()))
    }
}