package uk.nightlines.feature.weather.main_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.nightlines.core.common.ContainerState
import uk.nightlines.core.common.RootScreensCounterInteractor
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.day_api.DayScreenApi
import uk.nightlines.feature.weather.week_api.WeekScreenApi
import javax.inject.Inject

internal class WeatherViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @WeatherNavigationQualifier private val weatherNavigation: NavigationTypeCommand,
    private val rootScreens: RootScreensInteractor,
    private val weatherScreenCounterInteractor: RootScreensCounterInteractor,
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

    suspend fun onOpenNewWeatherScreenButtonClicked() {
        val weatherCounter = weatherScreenCounterInteractor.getCommandScreenCount()

        rootNavigation.navigate(NavigationForward(rootScreens.commandScreen(weatherCounter)))
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

    suspend fun onRemoveEditTextPositionChanged(text: String) {
        _state.emit(_state.value.copy(positionEditText = text))
    }

    suspend fun onRemoveScreensButtonClicked() {
        val list = state.value.positionEditText.split(',').map { it.toInt() }
        weatherNavigation.navigate(NavigationRemoveScreen(list))

        _state.emit(_state.value.copy(positionEditText = ""))
    }

    suspend fun onBackToRootButtonClicked() {
        weatherNavigation.navigate(NavigationBackToRoot)
    }

    suspend fun onBackToSecondScreenClicked(screen: Screen) {
        weatherNavigation.navigate(NavigationBackTo(screen))
    }

    suspend fun onReplaceButtonClicked() {
        val weatherCounter = weatherScreenCounterInteractor.getCommandScreenCount()

        rootNavigation.navigate(NavigationReplace(rootScreens.commandScreen(weatherCounter)))
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
        // TODO
        val weatherCounter = weatherScreenCounterInteractor.getCommandScreenCount()

        weatherNavigation.navigate(NavigationForward(rootScreens.commandScreen(weatherCounter)))
    }
}