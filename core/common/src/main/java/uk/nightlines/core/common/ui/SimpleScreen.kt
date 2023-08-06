package uk.nightlines.core.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    screenHashCode: Int,
    dependenciesProviderHashCode: Int,
    containerKeyScreen: String,
    keyScreen: String,
    state: SimpleState,
    onForwardButtonClicked: suspend () -> Unit,
    onReplaceButtonClicked: suspend () -> Unit,
    onSettingsButtonClicked: suspend () -> Unit,
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
                    "SCREEN HASHCODE: $screenHashCode\n" +
                    "DEPENDENCIES PROVIDER HASHCODE: $dependenciesProviderHashCode\n" +
                    "CONTAINER SCREEN KEY : $containerKeyScreen\n"
        )

        Row {
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
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 2.dp),
                onClick = {
                    coroutineScope.launch {
                        onSettingsButtonClicked()
                    }
                }) {
                Text("NEW CONTAINER")
            }
        }
    }
}