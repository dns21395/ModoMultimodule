package uk.nightlines.feature.weather.day_impl.ui

import androidx.lifecycle.ViewModel
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.feature.weather.main_api.OpenWeekScreenCommand
import uk.nightlines.feature.weather.main_api.WeatherNavigationQualifier
import javax.inject.Inject

internal class DayViewModel @Inject constructor(
    @WeatherNavigationQualifier private val navigation: Navigation
) : ViewModel() {

    suspend fun onOpenWeekScreenButtonClicked() {
        navigation.navigate(OpenWeekScreenCommand)
    }
}