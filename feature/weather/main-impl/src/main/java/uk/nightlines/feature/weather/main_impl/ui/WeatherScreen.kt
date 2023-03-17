package uk.nightlines.feature.weather.main_impl.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
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

        val state = viewModel.state.collectAsStateWithLifecycle()

        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            Log.d("GTA5", "[WEATHER] ***LAUNCHED*** HASHCODE : ${this.hashCode()}")

            viewModel.channelCommands.collectLatest {
                Log.d("GTA5", "[WEATHER] ***LAUNCHED*** CHANNEL : ${it}")
                navigate(it)
            }
        }

        CompositionLocalProvider(
            LocalDependenciesProvider provides componentHolder.component
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(state.value.backgroundColor)) {
                Text(text = state.value.emoji, style = MaterialTheme.typography.h1)
                Text(text = "WEATHER #$counter\n " +
                        "CONTAINER HASCODE : ${this@WeatherStack.hashCode()}\n" +
                        "SCREEN KEY : ${screenKey.value}\n" +
                        "STACK : ${navigationState.stack.map { it.screenKey.value }}")
                Button(onClick = {
                    coroutineScope.launch { viewModel.onOpenNewWeatherScreenButtonClicked() }
                }) {
                    Text(text = "Open New Weather Screen")
                }
                Button(onClick = {
                    coroutineScope.launch { viewModel.openNewStackButtonClicked() }
                }) {
                    Text(text = "Open New Stack of Screen in container ${screenKey.value}")
                }
                BasicTextField(
                    value = state.value.positionEditText,
                    onValueChange = { text -> coroutineScope.launch { viewModel.onRemoveEditTextPositionChanged(text) } },
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 64.dp) // margin left and right
                                .fillMaxWidth()
                                .background(
                                    color = Color(0xFF5E35B1),
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFFFBC02D),
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .padding(all = 16.dp), // inner padding
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Favorite icon",
                                tint = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.width(width = 8.dp))
                            innerTextField()
                        }
                    }
                )
                Button(onClick = {
                    coroutineScope.launch { viewModel.onRemoveScreensButtonClicked() }
                }) {
                    Text("Remove By positions ")
                }
                Button(onClick = { coroutineScope.launch { viewModel.onBackToRootButtonClicked() } }) {
                    Text("BACK TO ROOT")
                }
                TopScreenContent()
            }
        }
    }
}
