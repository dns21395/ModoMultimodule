package uk.nightlines.feature.weather.week_impl.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import uk.nightlines.core.navigation.*
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherScreens
import javax.inject.Inject

internal class WeekViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @WeatherNavigationQualifier private val locatlNavigation: Navigation,
    private val weatherScreens: WeatherScreens,
    private val weatherDependencies: WeatherDependencies,
    private val rootScreens: RootScreens
) : ViewModel() {

    suspend fun onOpenDayScreenButtonClicked() {
               Log.d("GTA5", "[WEEK] ViewModel. DEPS : ${weatherDependencies.hashCode()}")
        locatlNavigation.navigate(NavigationReplace(weatherScreens.getDayScreen()))
    }

    suspend fun onOpenSettingScreenButtonClicked() {
        rootNavigation.navigate(NavigationForward(rootScreens.settings()))
    }
}
