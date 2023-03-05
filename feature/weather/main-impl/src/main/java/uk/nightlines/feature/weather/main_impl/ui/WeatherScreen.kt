package uk.nightlines.feature.weather.main_impl.ui

import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.replace
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.navigate
import uk.nightlines.feature.weather.day_impl.ui.DayScreen
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.common.OpenDayScreenCommand
import uk.nightlines.feature.weather.common.OpenWeekScreenCommand
import uk.nightlines.feature.weather.main_impl.di.DaggerWeatherMainComponent
import uk.nightlines.feature.weather.week_impl.ui.WeekScreen

@Parcelize
internal class WeatherStack(
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor() :  this(StackNavModel(emptyList()))


    @Composable
    override fun Content() {
        val component = remember {
            DaggerWeatherMainComponent.builder().build()
        }
        val viewModel: WeatherViewModel = daggerViewModel {
            component.viewModel()
        }

        val commands = viewModel.navigationCommands.collectAsState(NavigationReplace(component.screenInteractor().getWeekScreen()))

        LaunchedEffect(key1 = commands.value) { navigate(commands.value) }

        CompositionLocalProvider(
            LocalDependenciesProvider provides component
        ) {
            TopScreenContent()
        }
    }
}
