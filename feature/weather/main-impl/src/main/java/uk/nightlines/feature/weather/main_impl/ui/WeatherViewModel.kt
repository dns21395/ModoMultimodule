package uk.nightlines.feature.weather.main_impl.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uk.nightlines.core.common.WeatherScreenCounterInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherScreens
import javax.inject.Inject

internal class WeatherViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @WeatherNavigationQualifier private val weatherNavigation: Navigation,
    private val rootScreens: RootScreens,
    private val weatherScreenCounterInteractor: WeatherScreenCounterInteractor,
    private val weatherScreens: WeatherScreens,
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherViewState())
    val state: StateFlow<WeatherViewState> = _state

    init {
        viewModelScope.launch {
            weatherNavigation.navigate(NavigationForward(weatherScreens.getWeekScreen()))
        }
    }

    val channelCommands: Flow<NavigationCommand> = weatherNavigation.commandsFlow

    suspend fun onOpenNewWeatherScreenButtonClicked() {
        val weatherCounter = weatherScreenCounterInteractor.getWeatherScreenCount()

        rootNavigation.navigate(NavigationForward(rootScreens.weather(weatherCounter)))
    }

    suspend fun openNewStackButtonClicked() {
        weatherNavigation.navigate(
            NavigationSetStack(
                listOf(
                    weatherScreens.getDayScreen(),
                    weatherScreens.getWeekScreen(),
                    weatherScreens.getDayScreen(),
                    weatherScreens.getWeekScreen()
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
        val weatherCounter = weatherScreenCounterInteractor.getWeatherScreenCount()

        rootNavigation.navigate(NavigationReplace(rootScreens.weather(weatherCounter)))
    }

    suspend fun onMultiForwardButtonClicked() {
        weatherNavigation.navigate(
            NavigationForward(
                weatherScreens.getDayScreen(),
                listOf(
                    weatherScreens.getWeekScreen(),
                    weatherScreens.getDayScreen(),
                    weatherScreens.getWeekScreen()
                )
            )
        )
    }

    suspend fun onNewRootButtonClicked() {
        weatherNavigation.navigate(NavigationNewStack(weatherScreens.getWeekScreen()))
    }

    suspend fun onContainerButtonClicked() {
        // TODO
//        val weatherCounter = weatherScreenCounterInteractor.getWeatherScreenCount()
//
//        weatherNavigation.navigate(NavigationForward(rootScreens.weather(weatherCounter)))
    }
}