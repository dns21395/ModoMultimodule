package uk.nightlines.feature.weather.main_impl.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.navigate
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.common.ScreenCounter
import uk.nightlines.feature.weather.main_impl.di.DaggerWeatherMainComponent

private const val KEY_COMPONENT = "KEY_WEATHER_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_WEATHER_VIEWMODEL"

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

        Log.d("GTA6", "-------------------------------\n[WEATHER] SCREEN KEY : ${stackNavModel.screenKey}\n" +
                "hashCode : ${this.hashCode()}")

        val componentHolder = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_COMPONENT") {
            Log.d("GTA5", "[WEATHER] component created")
            ComponentHolder(DaggerWeatherMainComponent.factory().create(coreProvider))
        }

        val viewModel: WeatherViewModel = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_VIEWMODEL") {
            Log.d("GTA5", "[WEATHER] dagger created. DEPS : ${componentHolder.component.hashCode()}")

            componentHolder.component.viewModel()
        }

        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            Log.d("GTA5", "[WEATHER] ***LAUNCHED*** HASHCODE : ${this.hashCode()}")

            viewModel.channelCommands.collectLatest {
                Log.d("GTA5", "[WEATHER] ***LAUNCHED*** CHANNEL : ${it}")
                navigate(it)
            }

            viewModel.flowCommands.collectLatest {
                Log.d("GTA5", "[WEATHER] ***LAUNCHED***  FLOW  : ${it}")
            }
        }

        CompositionLocalProvider(
            LocalDependenciesProvider provides componentHolder.component
        ) {
            Column {
                Text(text = "WEATHER #$counter\n" +
                        "CONTAINER HASCODE : ${this@WeatherStack.hashCode()}\n" +
                        "SCREEN KEY : ${stackNavModel.screenKey}$KEY_COMPONENT")
                Button(onClick = {
                    coroutineScope.launch(Dispatchers.Main) { viewModel.onOpenNewWeatherScreenButtonClicked() }
                }) {
                    Text(text = "Open New Weather Screen")
                }
                TopScreenContent()
            }
        }
    }
}
