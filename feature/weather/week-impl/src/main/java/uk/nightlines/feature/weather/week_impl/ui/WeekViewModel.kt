package uk.nightlines.feature.weather.week_impl.ui

import androidx.lifecycle.ViewModel
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.OpenSettingsCommand
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherScreenInteractor
import javax.inject.Inject

internal class WeekViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @WeatherNavigationQualifier private val locatlNavigation: Navigation,
    private val weatherScreenInteractor: WeatherScreenInteractor
) : ViewModel() {

    suspend fun onOpenDayScreenButtonClicked() {
//        Log.d("GTA5", "onOpenDayScreenButtonClicked\n" +
//                "weather navigation : ${navigation.hashCode()}")
        locatlNavigation.navigate(NavigationReplace(weatherScreenInteractor.getDayScreen()))
    }

    suspend fun onOpenSettingScreenButtonClicked() {
        rootNavigation.navigate(OpenSettingsCommand)
    }
}
