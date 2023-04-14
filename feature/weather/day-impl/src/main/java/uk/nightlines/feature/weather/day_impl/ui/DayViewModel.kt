package uk.nightlines.feature.weather.day_impl.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.core.navigation.setstack.NavigationForward
import uk.nightlines.core.navigation.setstack.NavigationReplace
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherScreens
import javax.inject.Inject

internal class DayViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @WeatherNavigationQualifier private val navigation: NavigationTypeCommand,
    private val weatherScreens: WeatherScreens,
    private val weatherDependencies: WeatherDependencies
) : ViewModel() {

    private val mutableState = MutableStateFlow(DayViewState())
    val state: StateFlow<DayViewState> = mutableState

    suspend fun onReplaceButtonClicked() {
        Log.d("GTA5", "[DAY] ViewModel DEPS : ${weatherDependencies.hashCode()}")
        navigation.navigate(NavigationReplace(weatherScreens.getWeekScreen()))
    }

    suspend fun onForwardButtonClicked() {
        navigation.navigate(NavigationForward(weatherScreens.getWeekScreen()))

    }

    suspend fun onOpenDialogButtonClicked() {
        rootNavigation.navigate(NavigationForward(DayDialog()))
    }

    suspend fun onTextChangedAction(text: String) {
        mutableState.value = mutableState.value.copy(editText = text)
    }
}