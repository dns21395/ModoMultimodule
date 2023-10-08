package uk.nightlines.feature.weather.container_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.common.state.ContainerState
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.complex.feature_api.ComplexFeatureApi
import uk.nightlines.feature.weather.common.FeatureNavigationQualifier
import javax.inject.Inject

internal class WeatherViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @FeatureNavigationQualifier private val complexNavigation: NavigationTypeCommand,
    private val rootScreens: RootScreensInteractor,
    private val complexFeatureApi: ComplexFeatureApi
) : ViewModel() {

    val navigationCommands: Flow<NavigationCommand> = complexNavigation.commandsFlow

    private val _state = MutableStateFlow(ContainerState())
    val state: StateFlow<ContainerState> = _state

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