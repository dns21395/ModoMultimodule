package uk.nightlines.feature.weather.container_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.state.ContainerState
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.one_api.DayScreenApi
import uk.nightlines.feature.weather.two_api.WeekScreenApi
import javax.inject.Inject

internal class WeatherViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @WeatherNavigationQualifier private val weatherNavigation: NavigationTypeCommand,
    private val rootScreens: RootScreensInteractor,
    private val weekScreenApi: WeekScreenApi,
    private val dayScreenApi: DayScreenApi
) : ViewModel() {

    val navigationCommands: Flow<NavigationCommand> = weatherNavigation.commandsFlow

    private val _state = MutableStateFlow(ContainerState())
    val state: StateFlow<ContainerState> = _state

    init {
        viewModelScope.launch {
            weatherNavigation.navigate(NavigationForward(weekScreenApi.getWeekScreen()))
        }
    }

    suspend fun onShowOptionsButtonClicked() {
        _state.emit(state.value.copy(isOptionsVisible = !state.value.isOptionsVisible))
    }

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

    suspend fun openNewStackButtonClicked() {
        weatherNavigation.navigate(
            NavigationSetStack(
                listOf(
                    dayScreenApi.getDayScreen(),
                    weekScreenApi.getWeekScreen(),
                    dayScreenApi.getDayScreen(),
                    weekScreenApi.getWeekScreen()
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
                dayScreenApi.getDayScreen(),
                listOf(
                    weekScreenApi.getWeekScreen(),
                    dayScreenApi.getDayScreen(),
                    weekScreenApi.getWeekScreen()
                )
            )
        )
    }

    suspend fun onNewRootButtonClicked() {
        weatherNavigation.navigate(NavigationNewStack(weekScreenApi.getWeekScreen()))
    }

    suspend fun onContainerButtonClicked() {
        weatherNavigation.navigate(NavigationForward(rootScreens.weatherScreen()))
    }
}