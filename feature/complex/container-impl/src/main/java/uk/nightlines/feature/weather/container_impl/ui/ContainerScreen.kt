package uk.nightlines.feature.weather.container_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.launch
import uk.nightlines.core.common.state.ContainerState

@Composable
fun ContainerScreenContent(
    title: String,
    state: ContainerState,
    screenKey: String,
    screenHashCode: String,
    navigationStack: List<Screen>,
    onShowOptionsButtonClicked: suspend () -> Unit,
    onForwardWeatherButtonClicked: suspend () -> Unit,
    onReplaceWeatherButtonClicked: suspend () -> Unit,
    onForwardSettingsButtonClicked: suspend () -> Unit,
    onReplaceSettingsButtonClicked: suspend () -> Unit,
    onRemoveByPositionsButtonClicked: suspend () -> Unit,
    onBackToSecondScreenButtonClicked: suspend (Screen) -> Unit,
    onBackToRootClicked: suspend () -> Unit,
    onNewStackButtonClicked: suspend () -> Unit,
    onMultiForwardButtonClicked: suspend () -> Unit,
    onNewRootButtonClicked: suspend () -> Unit,
    onContainerButtonClicked: suspend () -> Unit,
    topScreenContent: @Composable () -> Unit
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
                text = "$title ($screenKey)\n" +
                        "CONTAINER HASCODE : $screenHashCode\n" +
                        "STACK : ${navigationStack.map { it.screenKey.value }}"
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { coroutineScope.launch { onShowOptionsButtonClicked() } }
                ) {
                    Text(if (state.isOptionsVisible) "HIDE OPTIONS" else "SHOW OPTIONS")
                }
            }
            if (state.isOptionsVisible) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        "Root navigation actions",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h6
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        onClick = {
                            coroutineScope.launch { onForwardWeatherButtonClicked() }
                        }) {
                        Text(text = "FORWARD [WEATHER]")
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        onClick = {
                            coroutineScope.launch { onReplaceWeatherButtonClicked() }
                        }) {
                        Text(text = "REPLACE [WEATHER]")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        onClick = {
                            coroutineScope.launch { onForwardSettingsButtonClicked() }
                        }) {
                        Text(text = "FORWARD [SETTINGS]")
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        onClick = {
                            coroutineScope.launch { onReplaceSettingsButtonClicked() }
                        }) {
                        Text(text = "REPLACE [SETTINGS]")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        "Container navigation actions",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h6
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        onClick = {
                            coroutineScope.launch { onRemoveByPositionsButtonClicked() }
                        }) {
                        Text("REMOVE SCREENS 1 AND 3")
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
                                    navigationStack[1]
                                )
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text("BACK TO 2ND SCREEN", style = MaterialTheme.typography.overline)
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
            topScreenContent()
        }
    }

}