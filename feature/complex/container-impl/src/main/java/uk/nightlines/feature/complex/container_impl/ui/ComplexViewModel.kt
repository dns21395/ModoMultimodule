package uk.nightlines.feature.complex.container_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.feature.complex.feature_api.ComplexFeatureApi
import uk.nightlines.feature.complex.common.FeatureNavigationQualifier
import javax.inject.Inject

internal class ComplexViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @FeatureNavigationQualifier private val complexNavigation: Navigation,
    private val rootScreens: RootScreensInteractor,
    private val complexFeatureApi: ComplexFeatureApi
) : ViewModel() {

    val navigationCommands: Flow<NavigationCommand> = complexNavigation.commandsFlow

    private val _state = MutableStateFlow(ComplexState())
    val state: StateFlow<ComplexState> = _state

    init {
        viewModelScope.launch {
            complexNavigation.navigate(NavigationForward(complexFeatureApi.screen()))
        }
    }

    suspend fun onForwardWeatherButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.complexScreen()))
    }

    suspend fun onReplaceWeatherButtonClicked() {
        rootNavigation.navigate(NavigationReplace(rootScreens.complexScreen()))
    }

    suspend fun onForwardSettingsButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.simpleScreen()))
    }

    suspend fun onReplaceSettingsButtonClicked() {
        rootNavigation.navigate(NavigationReplace(rootScreens.simpleScreen()))
    }
}