package uk.nightlines.feature.weather.main_impl.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.navigate
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.common.ScreenCounter
import uk.nightlines.feature.weather.main_impl.di.DaggerWeatherMainComponent

@Parcelize
internal class WeatherStack(
    private val stackNavModel: StackNavModel,
    private val counter: Int
) : StackScreen(stackNavModel), ScreenCounter {

    constructor(counter: Int) :  this(StackNavModel(emptyList()), counter)

    override fun getCounter(): Int = counter

    @Composable
    override fun Content() {


        val coreProvider = LocalCoreProvider.current

        val component = remember {
            DaggerWeatherMainComponent.factory().create(coreProvider)
        }

        val viewModel: WeatherViewModel = daggerViewModel {
            component.viewModel()
        }

        Log.d("GTA5", "Weather Stack. Component hashCode : ${component.hashCode()}")

        val coroutineScope = rememberCoroutineScope()

        val commands = viewModel.navigationCommands.collectAsState(NavigationReplace(component.screenInteractor().getWeekScreen()))

        LaunchedEffect(key1 = commands.value) { navigate(commands.value) }

        CompositionLocalProvider(
            LocalDependenciesProvider provides component,
        ) {
            Column {
                Row {
                    Text(text = "WEATHER #$counter")
                    Button(onClick = {
                        coroutineScope.launch { viewModel.onOpenNewWeatherScreenButtonClicked() }
                    }) {
                        Text(text = "Open New Weather Screen")
                    }
                }
                TopScreenContent()
            }
        }
    }
}
