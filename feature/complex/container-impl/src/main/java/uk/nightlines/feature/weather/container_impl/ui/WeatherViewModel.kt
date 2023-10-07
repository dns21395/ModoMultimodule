package uk.nightlines.feature.weather.container_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.common.state.ContainerState
import uk.nightlines.core.navigation.NavigationBackTo
import uk.nightlines.core.navigation.NavigationBackToRoot
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationNewStack
import uk.nightlines.core.navigation.NavigationRemoveScreen
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.NavigationSetStack
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.FeatureNavigationQualifier
import uk.nightlines.feature.weather.one_api.ComplexFeatureApi
import javax.inject.Inject

internal class WeatherViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @FeatureNavigationQualifier private val weatherNavigation: NavigationTypeCommand,
    private val rootScreens: RootScreensInteractor,
    private val screenApi: ComplexFeatureApi
) : ViewModel() {

    val navigationCommands: Flow<NavigationCommand> = weatherNavigation.commandsFlow

    private val _state = MutableStateFlow(ContainerState())
    val state: StateFlow<ContainerState> = _state

    init {
        viewModelScope.launch {
            weatherNavigation.navigate(NavigationForward(screenApi.screen()))
        }
    }

    suspend fun onShowOptionsButtonClicked() {
        _state.emit(state.value.copy(isOptionsVisible = !state.value.isOptionsVisible))
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

    suspend fun openNewStackButtonClicked() {
        weatherNavigation.navigate(
            NavigationSetStack(
                listOf(
                    screenApi.screen(),
                    screenApi.screen(),
                )
            )
        )
    }

    suspend fun onRemoveFirstAndThirdScreensButtonClicked() {
        weatherNavigation.navigate(NavigationRemoveScreen(listOf(0, 2)))
    }

    suspend fun onBackToRootButtonClicked() {
        weatherNavigation.navigate(NavigationBackToRoot)
    }

    suspend fun onBackToSecondScreenClicked(screen: Screen) {
        weatherNavigation.navigate(NavigationBackTo(screen))
    }

    suspend fun onMultiForwardButtonClicked() {
        weatherNavigation.navigate(
            NavigationForward(
                screenApi.screen(),
                listOf(
                    screenApi.screen(),
                )
            )
        )
    }

    suspend fun onNewRootButtonClicked() {
        weatherNavigation.navigate(NavigationNewStack(screenApi.screen()))
    }

    suspend fun onContainerButtonClicked() {
        weatherNavigation.navigate(NavigationForward(rootScreens.complexScreen()))
    }
}