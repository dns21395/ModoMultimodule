package uk.nightlines.feature.weather.main_impl.ui

import androidx.lifecycle.ViewModel
import uk.nightlines.feature.weather.day_api.DayApi
import javax.inject.Inject

internal class WeatherViewModel @Inject constructor(
    private val dayApi: DayApi
): ViewModel() {


}