package uk.nightlines.feature.weather.day_impl.ui

import androidx.lifecycle.ViewModel
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.feature.weather.common.WeatherNavigationQualifier
import uk.nightlines.feature.weather.common.WeatherScreenInteractor
import javax.inject.Inject

internal class DayViewModel @Inject constructor(
    @WeatherNavigationQualifier private val navigation: Navigation,
    private val screenInteractor: WeatherScreenInteractor
) : ViewModel() {

    suspend fun onOpenWeekScreenButtonClicked() {
        navigation.navigate(NavigationReplace(screenInteractor.getWeekScreen()))
    }
}