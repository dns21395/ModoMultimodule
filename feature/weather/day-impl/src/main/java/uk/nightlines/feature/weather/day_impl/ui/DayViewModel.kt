package uk.nightlines.feature.weather.day_impl.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.NavigationForward
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherScreenInteractor
import javax.inject.Inject

internal class DayViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @WeatherNavigationQualifier private val navigation: Navigation,
    private val screenInteractor: WeatherScreenInteractor,
    private val weatherDependencies: WeatherDependencies
) : ViewModel() {

    suspend fun onOpenWeekScreenButtonClicked() {
        Log.d("GTA5", "[DAY] ViewModel DEPS : ${weatherDependencies.hashCode()}")
        navigation.navigate(NavigationReplace(screenInteractor.getWeekScreen()))
    }

    suspend fun onOpenDialogButtonClicked() {
        rootNavigation.navigate(NavigationForward(DayDialog()))
    }
}