package uk.nightlines.feature.weather.main_impl.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
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
import com.github.terrakok.modo.Screen
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
    private val counter: Int,
) : StackScreen(stackNavModel), ScreenCounter {

    constructor(counter: Int) : this(StackNavModel(emptyList()), counter)

    override fun getCounter(): Int = counter

    @Composable
    override fun Content() {
        val coreProvider = LocalCoreProvider.current

        Log.d(
            "GTA6",
            "-------------------------------\n[WEATHER] SCREEN KEY : ${stackNavModel.screenKey}\n" +
                    "hashCode : ${this.hashCode()}"
        )

        val componentHolder = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_COMPONENT") {
            Log.d("GTA5", "[WEATHER] component created")
            ComponentHolder(DaggerWeatherMainComponent.factory().create(coreProvider))
        }

        val viewModel: WeatherViewModel =
            daggerViewModel(key = "${stackNavModel.screenKey}$KEY_VIEWMODEL") {
                Log.d(
                    "GTA5",
                    "[WEATHER] dagger created. DEPS : ${componentHolder.component.hashCode()}"
                )

                componentHolder.component.viewModel()
            }

        val state = viewModel.state.collectAsStateWithLifecycle()

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
            WeatherScreenContent(
                state = state.value,
                onShowOptionsButtonClicked = { viewModel.onShowOptionsButtonClicked() },
                onForwardButtonClicked = { viewModel.onOpenNewWeatherScreenButtonClicked() },
                onReplaceButtonClicked = { viewModel.onReplaceButtonClicked() },
                onRemoveByPositionsEditTextChanged = { viewModel.onRemoveEditTextPositionChanged(it) },
                onRemoveByPositionsButtonClicked = { viewModel.onRemoveScreensButtonClicked() },
                onBackToSecondScreenButtonClicked = { viewModel.onBackToSecondScreenClicked(it) },
                onBackToRootClicked = { viewModel.onBackToRootButtonClicked() },
                onNewStackButtonClicked = { viewModel.openNewStackButtonClicked() },
                onMultiForwardButtonClicked = { viewModel.onMultiForwardButtonClicked() },
                onNewRootButtonClicked = { viewModel.onNewRootButtonClicked() },
                onContainerButtonClicked = { viewModel.onContainerButtonClicked() })
        }
    }

    @Composable
    fun WeatherScreenContent(
        state: WeatherViewState,
        onShowOptionsButtonClicked: suspend () -> Unit,
        onForwardButtonClicked: suspend () -> Unit,
        onReplaceButtonClicked: suspend () -> Unit,
        onRemoveByPositionsEditTextChanged: suspend (String) -> Unit,
        onRemoveByPositionsButtonClicked: suspend () -> Unit,
        onBackToSecondScreenButtonClicked: suspend (Screen) -> Unit,
        onBackToRootClicked: suspend () -> Unit,
        onNewStackButtonClicked: suspend () -> Unit,
        onMultiForwardButtonClicked: suspend () -> Unit,
        onNewRootButtonClicked: suspend () -> Unit,
        onContainerButtonClicked: suspend () -> Unit,
    ) {
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(state.backgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Gray)
                    .padding(2.dp)
                    .background(Color.White)
            ) {
                Text(text = state.emoji, style = MaterialTheme.typography.h4)
                Text(
                    text = "WEATHER #$counter (${screenKey.value})\n " +
                            "CONTAINER HASCODE : ${this@WeatherStack.hashCode()}\n" +
                            "STACK : ${navigationState.stack.map { it.screenKey.value }}"
                )

                Button(onClick = { coroutineScope.launch { onShowOptionsButtonClicked() } }) {
                    Text(if (state.isOptionsVisible) "HIDE OPTIONS" else "SHOW OPTIONS")
                }

                if (state.isOptionsVisible) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            onClick = {
                                coroutineScope.launch { onForwardButtonClicked() }
                            }) {
                            Text(text = "FORWARD")
                        }
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            onClick = {
                                coroutineScope.launch { onReplaceButtonClicked() }
                            }) {
                            Text(text = "REPLACE")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        BasicTextField(
                            value = state.positionEditText,
                            onValueChange = { text ->
                                coroutineScope.launch { onRemoveByPositionsEditTextChanged(text) }
                            },
                            decorationBox = { innerTextField ->
                                Row(
                                    modifier = Modifier
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
                                        .padding(all = 16.dp),
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
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp)
                        )
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                            onClick = {
                                coroutineScope.launch { onRemoveByPositionsButtonClicked() }
                            }) {
                            Text("REMOVE BY POSITIONS")
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    onBackToSecondScreenButtonClicked(
                                        navigationState.stack[1]
                                    )
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp)
                        ) {
                            Text("BACK TO 2ND SCREEN")
                        }
                        Button(
                            onClick = { coroutineScope.launch { onBackToRootClicked() } },
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp)
                        ) {
                            Text("BACK TO ROOT")
                        }
                    }
                    Row {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            onClick = {
                                coroutineScope.launch { onNewStackButtonClicked() }
                            }) {
                            Text(text = "SET STACK")
                        }
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            onClick = {
                                coroutineScope.launch { onMultiForwardButtonClicked() }
                            }) {
                            Text(text = "MULTI FORWARD")
                        }
                    }
                    Row {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            onClick = {
                                coroutineScope.launch { onNewRootButtonClicked() }
                            }) {
                            Text(text = "NEW ROOT")
                        }
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            onClick = {
                                coroutineScope.launch { onContainerButtonClicked() }
                            }) {
                            Text(text = "CONTAINER")
                        }
                    }
                }

                TopScreenContent()
            }
        }

    }
}
