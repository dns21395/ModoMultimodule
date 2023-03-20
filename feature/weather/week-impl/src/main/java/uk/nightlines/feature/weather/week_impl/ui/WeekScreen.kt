package uk.nightlines.feature.weather.week_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
import uk.nightlines.feature.weather.week_impl.di.DaggerWeekComponent

private const val KEY_COMPONENT = "KEY_WEATHER_WEEK_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_WEATHER_WEEK_VIEWMODEL"

@Parcelize
class WeekScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        WeekContent(
            this@WeekScreen.hashCode(),
            screenKey = screenKey
        )
    }
}

@Composable
fun WeekContent(
    screenHashCode: Int,
    screenKey: ScreenKey,
) {
    val coreProvider = LocalCoreProvider.current
    val screen = LocalContainerScreen.current
    val weatherDependencies = LocalDependenciesProvider.current

    val component = daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT$screenHashCode") {
        ComponentHolder(DaggerWeekComponent.factory().create(coreProvider, weatherDependencies))
    }

    val viewModel: WeekViewModel =
        daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL$screenHashCode") {
            component.component.viewModel()
        }

    val state = viewModel.state.collectAsStateWithLifecycle()

    WeekStateContent(
        screenHashCode = screenHashCode,
        weatherDependenciesHashCode = weatherDependencies.hashCode(),
        containerKeyScreen = screen.screenKey.value,
        keyScreen = screenKey.value,
        state = state.value,
        onForwardButtonClicked = { viewModel.onForwardButtonClicked() },
        onReplaceButtonClicked = { viewModel.onReplaceButtonClicked() },
        onSettingsButtonClicked = { viewModel.onOpenSettingScreenButtonClicked() })
}

@Composable
fun WeekStateContent(
    screenHashCode: Int,
    weatherDependenciesHashCode: Int,
    containerKeyScreen: String,
    keyScreen: String,
    state: WeekState,
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
            "WEEK SCREEN ($keyScreen)\n" +
                    "HASHCODE: $screenHashCode\n" +
                    "DEPENDENCIES PROVIDER HASHCODE: $weatherDependenciesHashCode\n" +
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
                Text("SETTINGS")
            }
        }
    }
}