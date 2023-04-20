package uk.nightlines.feature.weather.day_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.day_impl.di.DaggerDayComponent

private const val KEY_COMPONENT = "KEY_WEATHER_DAY_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_WEATHER_DAY_VIEWMODEL"

@Parcelize
class DayScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        DayContent(
            this.hashCode(),
            screenKey
        )
    }
}

@Composable
fun DayContent(
    screenHashCode: Int,
    screenKey: ScreenKey,
) {
    val coreProvider = LocalCoreProvider.current
    val screen = LocalContainerScreen.current
    val weatherDependencies = LocalDependenciesProvider.current

    val component = daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT$screenHashCode") {
        ComponentHolder(DaggerDayComponent.factory().create(coreProvider, weatherDependencies))
    }

    val viewModel =
        daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL$screenHashCode") { component.component.viewModel() }

    val state = viewModel.state.collectAsStateWithLifecycle()

    DayContentState(
        screenHashCode = screenHashCode,
        weatherDependenciesHashCode = weatherDependencies.hashCode(),
        containerScreenKey = screen.screenKey.value,
        screenKey = screenKey.value,
        state = state.value,
        onForwardButtonClicked = { viewModel.onForwardButtonClicked() },
        onReplaceButtonClicked = { viewModel.onReplaceButtonClicked() },
        onOpenDialogButtonClicked = { viewModel.onOpenDialogButtonClicked() },
        onEditTextChanged = { viewModel.onTextChangedAction(it) }
    )
}

@Composable
fun DayContentState(
    screenHashCode: Int,
    weatherDependenciesHashCode: Int,
    containerScreenKey: String,
    screenKey: String,
    state: DayViewState,
    onForwardButtonClicked: suspend () -> Unit,
    onReplaceButtonClicked: suspend () -> Unit,
    onOpenDialogButtonClicked: suspend () -> Unit,
    onEditTextChanged: suspend (String) -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(state.backgroundColor)
    ) {
        Text(
            text = state.emoji,
            style = MaterialTheme.typography.h4
        )
        Text(
            "DAY SCREEN ($screenKey) \n" +
                    "HASHCODE: $screenHashCode\n" +
                    "DEPENDENCIES PROVIDER HASHCODE: $weatherDependenciesHashCode\n" +
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
