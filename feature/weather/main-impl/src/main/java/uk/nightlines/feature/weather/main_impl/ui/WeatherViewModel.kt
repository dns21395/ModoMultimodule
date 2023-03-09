package uk.nightlines.feature.weather.main_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import uk.nightlines.core.common.WeatherScreenCounterInteractor
import uk.nightlines.core.navigation.*
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import javax.inject.Inject

internal class WeatherViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @WeatherNavigationQualifier private val weatherNavigation: Navigation,
    private val rootScreens: RootScreens,
    private val weatherScreenCounterInteractor: WeatherScreenCounterInteractor
): ViewModel() {

    val navigationCommands: SharedFlow<NavigationCommand> = weatherNavigation.commandsFlow

    suspend fun onOpenNewWeatherScreenButtonClicked() {
        weatherScreenCounterInteractor.incrementScreenCount()
        val weatherCounter = weatherScreenCounterInteractor.getScreenCount()

        rootNavigation.navigate(NavigationForward(rootScreens.weather(weatherCounter)))
    }

}