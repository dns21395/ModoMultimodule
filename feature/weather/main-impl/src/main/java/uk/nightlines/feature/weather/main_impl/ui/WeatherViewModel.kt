package uk.nightlines.feature.weather.main_impl.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import uk.nightlines.core.navigation.Navigation
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.feature.weather.main_api.WeatherNavigationQualifier
import javax.inject.Inject

internal class WeatherViewModel @Inject constructor(
    @WeatherNavigationQualifier private val navigation: Navigation
): ViewModel() {

    val navigationCommands: SharedFlow<NavigationCommand> = navigation.commandsFlow

}