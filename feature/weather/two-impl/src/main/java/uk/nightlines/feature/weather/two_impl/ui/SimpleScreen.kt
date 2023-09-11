package uk.nightlines.feature.weather.two_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.nightlines.core.common.state.SimpleState

@Composable
fun SimpleScreenContent(
    screenName: String,
    containerKeyScreen: String,
    keyScreen: String,
    state: SimpleState,
    onForwardButtonClicked: suspend () -> Unit,
    onReplaceButtonClicked: suspend () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(state.color)
    ) {
        Text(
            text = state.emoji,
            style = MaterialTheme.typography.h4
        )
        Text(
            "$screenName ($keyScreen)\n" +
                    "CONTAINER SCREEN KEY : $containerKeyScreen\n"
        )
        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp),
                onClick = {
                    coroutineScope.launch(Dispatchers.Main) {
                        onReplaceButtonClicked()
                    }
                }) {
                Text("REPLACE")
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp),
                onClick = {
                    coroutineScope.launch(Dispatchers.Main) {
                        onForwardButtonClicked()
                    }
                }) {
                Text("FORWARD")
            }
        }
    }
}