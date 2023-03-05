package uk.nightlines.feature.weather.week_impl.ui

import androidx.lifecycle.ViewModel
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.OpenSettingsCommand
import uk.nightlines.core.navigation.RootNavigationQualifier
import uk.nightlines.feature.weather.main_api.OpenDayScreenCommand
import uk.nightlines.feature.weather.main_api.WeatherNavigationQualifier
import javax.inject.Inject

internal class WeekViewModel @Inject constructor(
    @RootNavigationQualifier private val rootNavigation: Navigation,
    @WeatherNavigationQualifier private val navigation: Navigation
) : ViewModel() {

    suspend fun onOpenDayScreenButtonClicked() {
        navigation.navigate(OpenDayScreenCommand)
    }

    suspend fun onOpenSettingScreenButtonClicked() {
        rootNavigation.navigate(OpenSettingsCommand)
    }
}
