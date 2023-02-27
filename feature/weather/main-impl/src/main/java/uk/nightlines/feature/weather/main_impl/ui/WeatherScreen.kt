package uk.nightlines.feature.weather.main_impl.ui

import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.replace
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.feature.weather.day_impl.DayScreen
import uk.nightlines.feature.weather.main_api.LocalWeatherNavigationProvider
import uk.nightlines.feature.weather.main_api.OpenDayScreenCommand
import uk.nightlines.feature.weather.main_api.OpenWeekScreenCommand
import uk.nightlines.feature.weather.main_impl.di.DaggerWeatherMainComponent
import uk.nightlines.feature.weather.week_impl.WeekScreen

@Parcelize
class WeatherStack(
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor() :  this(StackNavModel(emptyList()))


    @Composable
    override fun Content() {
        val component = DaggerWeatherMainComponent.builder().build()

        var currentCommand by remember {
            mutableStateOf<NavigationCommand>(OpenWeekScreenCommand)
        }

        LaunchedEffect(key1 = currentCommand) {
            when (currentCommand) {
                OpenDayScreenCommand -> replace(DayScreen())
                OpenWeekScreenCommand -> replace(WeekScreen())
            }
        }

        LaunchedEffect(currentCommand) {
            component.getNavigation().commandsFlow.collect { command ->
                currentCommand = command
            }
        }

        CompositionLocalProvider(
            LocalWeatherNavigationProvider provides component.getNavigation()
        ) {
            TopScreenContent()
        }
    }
}
