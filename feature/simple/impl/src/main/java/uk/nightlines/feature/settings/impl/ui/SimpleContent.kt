package uk.nightlines.feature.settings.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
internal fun SimpleContent(
    state: SimpleViewState,
    screenKey: String,
    onForwardSimpleButtonClicked: suspend () -> Unit,
    onReplaceSimpleButtonClicked: suspend () -> Unit,
    onForwardComplexButtonClicked: suspend () -> Unit,
    onReplaceComplexButtonClicked: suspend () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(state.backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "SIMPLE (${screenKey}) ${state.emoji}",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h6
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 8.dp),
                onClick = {
                    coroutineScope.launch { onForwardSimpleButtonClicked() }
                }) {
                Text(text = "FORWARD [SIMPLE]")
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 16.dp),
                onClick = {
                    coroutineScope.launch { onReplaceSimpleButtonClicked() }
                }) {
                Text(text = "REPLACE [SIMPLE]")
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 8.dp),
                onClick = {
                    coroutineScope.launch { onForwardComplexButtonClicked() }
                }) {
                Text(text = "FORWARD [COMPLEX]")
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 16.dp),
                onClick = {
                    coroutineScope.launch { onReplaceComplexButtonClicked() }
                }) {
                Text(text = "REPLACE [COMPLEX]")
            }
        }
    }
}