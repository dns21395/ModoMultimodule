package uk.nightlines.feature.weather.main_impl.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        weatherNavigation.navigate(NavigationNewStack(
            listOf(
                weatherScreens.getDayScreen(),
                weatherScreens.getWeekScreen(),
                weatherScreens.getDayScreen(),
                weatherScreens.getWeekScreen()
            )
        ))
    }
}