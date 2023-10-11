package uk.nightlines.feature.weather.container_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.Screen
import kotlinx.coroutines.launch

@Composable
fun ContainerScreenContent(
    title: String,
    state: ComplexState,
    screenKey: String,
    navigationStack: List<Screen>,
    onForwardWeatherButtonClicked: suspend () -> Unit,
    onReplaceWeatherButtonClicked: suspend () -> Unit,
    onForwardSettingsButtonClicked: suspend () -> Unit,
    onReplaceSettingsButtonClicked: suspend () -> Unit,
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
                        "STACK : ${navigationStack.map { it.screenKey.value }}"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
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
                    Text(text = "FORWARD [COMPLEX]")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    onClick = {
                        coroutineScope.launch { onReplaceWeatherButtonClicked() }
                    }) {
                    Text(text = "REPLACE [COMPLEX]")
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
                    Text(text = "FORWARD [SIMPLE]")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    onClick = {
                        coroutineScope.launch { onReplaceSettingsButtonClicked() }
                    }) {
                    Text(text = "REPLACE [SIMPLE]")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            topScreenContent()
        }
    }

}