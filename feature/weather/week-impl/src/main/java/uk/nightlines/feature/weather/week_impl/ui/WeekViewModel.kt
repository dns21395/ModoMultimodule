package uk.nightlines.feature.weather.week_impl.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.nightlines.core.common.RootScreensInteractor
import uk.nightlines.core.common.RootScreensCounterInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.core.navigation.setstack.NavigationForward
import uk.nightlines.core.navigation.setstack.NavigationReplace
import uk.nightlines.core.navigation.command.NavigationTypeCommand
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherScreens
import javax.inject.Inject

internal class WeekViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: NavigationTypeCommand,
    @WeatherNavigationQualifier private val locatlNavigation: NavigationTypeCommand,
    private val weatherScreens: WeatherScreens,
    private val weatherDependencies: WeatherDependencies,
    private val rootScreens: RootScreensInteractor,
    private val weatherScreenCounterInteractor: RootScreensCounterInteractor
) : ViewModel() {

    private val mutableState = MutableStateFlow(WeekState())
    val state: StateFlow<WeekState> = mutableState

    suspend fun onReplaceButtonClicked() {
               Log.d("GTA5", "[WEEK] ViewModel. DEPS : ${weatherDependencies.hashCode()}")
        locatlNavigation.navigate(NavigationReplace(weatherScreens.getDayScreen()))
    }

    suspend fun onForwardButtonClicked() {
        locatlNavigation.navigate(NavigationForward(weatherScreens.getDayScreen()))
    }

    suspend fun onOpenSettingScreenButtonClicked() {
        val settingsCount = weatherScreenCounterInteractor.getSetStackScreenCount()
        rootNavigation.navigate(NavigationForward(rootScreens.setStackScreen(settingsCount)))
    }
}
