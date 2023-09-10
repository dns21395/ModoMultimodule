package uk.nightlines.feature.weather.week_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.state.SimpleState
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.day_api.DayScreenApi
import javax.inject.Inject

internal class WeekViewModel @Inject constructor(
    @WeatherNavigationQualifier private val locatlNavigation: NavigationTypeCommand,
    private val dayApi: DayScreenApi,
) : ViewModel() {

    private val mutableState = MutableStateFlow(SimpleState())
    val state: StateFlow<SimpleState> = mutableState

    suspend fun onReplaceButtonClicked() {
        locatlNavigation.navigate(NavigationReplace(dayApi.getDayScreen()))
    }

    suspend fun onForwardButtonClicked() {
        locatlNavigation.navigate(NavigationForward(dayApi.getDayScreen()))
    }

}
