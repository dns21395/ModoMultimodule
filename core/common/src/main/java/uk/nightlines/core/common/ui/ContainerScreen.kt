package uk.nightlines.core.common.ui

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                text = "$title ($screenKey)\n " +
                        "CONTAINER HASCODE : $screenHashCode\n" +
                        "STACK : ${navigationStack.map { it.screenKey.value }}"
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
                                    navigationStack[1]
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

            topScreenContent()
        }
    }

}