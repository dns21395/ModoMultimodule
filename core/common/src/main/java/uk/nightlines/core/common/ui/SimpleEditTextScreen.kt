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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.nightlines.core.common.state.SimpleEditTextState

@Composable
fun SimpleEditTextScreen(
    screenName: String,
    state: SimpleEditTextState,
    screenHashCode: Int,
    containerScreenKey: String,
    screenKey: String,
    onForwardButtonClicked: suspend () -> Unit,
    onReplaceButtonClicked: suspend () -> Unit,
    onOpenDialogButtonClicked: suspend () -> Unit,
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
                    "SCREEN HASHCODE: $screenHashCode\n" +
                    "CONTAINER SCREEN KEY : $containerScreenKey\n"
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
                        onOpenDialogButtonClicked()
                    }
                }) {
                Text("DIALOG")
            }
        }

        BasicTextField(
            modifier = Modifier.padding(top = 2.dp),
            value = state.editText,
            onValueChange = { text -> coroutineScope.launch { onEditTextChanged(text) } },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .padding(horizontal = 64.dp) // margin left and right
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFD2F3F2),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = Color(0xFFAAE9E6),
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
    }
}