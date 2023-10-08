package uk.nightlines.feature.complex.feature_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.nightlines.core.common.state.SimpleEditTextState

@Composable
fun SimpleEditTextScreen(
    screenName: String,
    state: SimpleEditTextState,
    containerScreenKey: String,
    screenKey: String,
    onForwardButtonClicked: suspend () -> Unit,
    onReplaceButtonClicked: suspend () -> Unit,
    onEditTextChanged: suspend (String) -> Unit,
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
            "$screenName ($screenKey) \n" +
                    "CONTAINER : $containerScreenKey\n"
        )

        BasicTextField(
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            value = state.editText,
            onValueChange = { text -> coroutineScope.launch { onEditTextChanged(text) } },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFE0E0E0),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = Color(0xFF424242),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(all = 16.dp),
                ) {
                    if (state.editText.isEmpty()) {
                        Text(
                            text = "Type something",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                    innerTextField()
                }
            }
        )

        Row {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 8.dp),
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