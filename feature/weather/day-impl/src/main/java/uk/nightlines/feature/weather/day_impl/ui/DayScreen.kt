package uk.nightlines.feature.weather.day_impl.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
        DayContent(this.hashCode())
    }
}

@Composable
fun DayContent(screenHashCode: Int) {
    val coreProvider = LocalCoreProvider.current
    val screen = LocalContainerScreen.current
    val weatherDependencies = LocalDependenciesProvider.current


    val component = daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT$screenHashCode") {
        ComponentHolder(DaggerDayComponent.factory().create(coreProvider, weatherDependencies))
    }

    Log.d("GTA5", "[DAY] DEPS ; ${weatherDependencies.hashCode()}")
    Log.d("GTA6", "[DAY] SCREEN KEY : ${screen.screenKey}")

    val viewModel =
        daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL$screenHashCode") { component.component.viewModel() }

    val state = viewModel.state.collectAsState(DayViewState())

    val coroutineScope = rememberCoroutineScope()

    Column {
        Text("DayScreen\n" +
                "HASHCODE : ${screenHashCode}\n" +
                "SCREEN_KEY = ${screen.screenKey}$KEY_COMPONENT")
        Button(onClick = {
            coroutineScope.launch(Dispatchers.Main) {
                viewModel.onOpenWeekScreenButtonClicked()
            }
        }) {
            Text("Open Week Screen")
        }
        Button(onClick = {
            coroutineScope.launch {
                viewModel.onOpenDialogButtonClicked()
            }
        }) {
            Text("Open Dialog")
        }
        BasicTextField(
            value = state.value.editText,
            onValueChange = { text -> coroutineScope.launch { viewModel.onTextChangedAction(text) } },
        )
    }
}
