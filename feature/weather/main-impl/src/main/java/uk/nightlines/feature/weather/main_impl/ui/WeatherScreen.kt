package uk.nightlines.feature.weather.main_impl.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
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

        Log.d("GTA6", "-------------------------------\n[WEATHER] SCREEN KEY : ${stackNavModel.screenKey}")

        val component = daggerViewModel(key = "${stackNavModel.screenKey}WEATHER_COMP") {
            Log.d("GTA5", "[WEATHER] component created")
            ComponentHolder(DaggerWeatherMainComponent.factory().create(coreProvider))
        }

        val viewModel: WeatherViewModel = daggerViewModel(key = "${stackNavModel.screenKey}WEATHER") {
            Log.d("GTA5", "[WEATHER] dagger created. DEPS : ${component.hashCode()}")

            component.component.viewModel()
        }

        val coroutineScope = rememberCoroutineScope()

        val commands = viewModel.navigationCommands.collectAsState(NavigationReplace(component.component.weatherScreens().getWeekScreen()))

        LaunchedEffect(key1 = commands.value) {
            navigate(commands.value)
        }

        CompositionLocalProvider(
            LocalDependenciesProvider provides component.component
        ) {
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
}
