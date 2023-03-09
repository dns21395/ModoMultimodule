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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.navigate
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.common.ScreenCounter
import uk.nightlines.feature.weather.common.WeatherDependencies
import uk.nightlines.feature.weather.common.WeatherDependenciesProvider
import uk.nightlines.feature.weather.main_impl.di.DaggerWeatherMainComponent

@Parcelize
internal class WeatherStack(
    private val stackNavModel: StackNavModel,
    private val counter: Int
) : StackScreen(stackNavModel), ScreenCounter, WeatherDependenciesProvider {

    @IgnoredOnParcel
    private lateinit var weatherDependencies: WeatherDependencies

    constructor(counter: Int) :  this(StackNavModel(emptyList()), counter)

    override fun getCounter(): Int = counter

    @Composable
    override fun Content() {
        val coreProvider = LocalCoreProvider.current

        val component = remember {
            DaggerWeatherMainComponent.factory().create(coreProvider)
        }

        val viewModel: WeatherViewModel = remember {
            component.viewModel()
        }

        LaunchedEffect("one") {
            Log.d("GTA5", "SETTINGS WEATHER DEPS : ${component.hashCode()}")
            weatherDependencies = component
        }

        val coroutineScope = rememberCoroutineScope()

        val commands = viewModel.navigationCommands.collectAsState(NavigationReplace(component.screenInteractor().getWeekScreen()))

        LaunchedEffect(key1 = commands.value) {

            Log.d("GTA5", "LAUNCHED EFFECT : ${weatherDependencies.hashCode()}\n" +
                    "command : ${commands.value}")

            navigate(commands.value)
        }

        CompositionLocalProvider {
            Column {
                Row {
                    Text(text = "WEATHER #$counter")
                    Button(onClick = {
                        coroutineScope.launch(Dispatchers.Main) { viewModel.onOpenNewWeatherScreenButtonClicked() }
                    }) {
                        Text(text = "Open New Weather Screen")
                    }
                }
                TopScreenContent()
            }
        }
    }

    override fun weatherDependencies(): WeatherDependencies {
        return weatherDependencies
    }
}
