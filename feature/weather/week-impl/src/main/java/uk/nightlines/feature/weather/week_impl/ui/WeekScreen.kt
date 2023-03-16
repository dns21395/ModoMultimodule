package uk.nightlines.feature.weather.week_impl.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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
    override val screenKey: ScreenKey = generateScreenKey()
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
    screenKey: ScreenKey
) {
    val coreProvider = LocalCoreProvider.current
    val screen = LocalContainerScreen.current
    val weatherDependencies = LocalDependenciesProvider.current

    Log.d("GTA5", "[WEEK] DEPS : ${weatherDependencies.hashCode()}")

    Log.d("GTA6", "[WEEK] SCREEN KEY : ${screen.screenKey}")

    val coroutineScope = rememberCoroutineScope()

    val component = daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT$screenHashCode" ){
        Log.d("GTA5", "[WEEK] component created. DEPS: ${weatherDependencies.hashCode()}")
        ComponentHolder(DaggerWeekComponent.factory().create(coreProvider, weatherDependencies))

    }

    val viewModel: WeekViewModel = daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL$screenHashCode") {
        Log.d("GTA5", "[WEEK] dagger created. DEPS: ${weatherDependencies.hashCode()}")

        component.component.viewModel()
    }

    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().background(state.value.color)) {
        Text(
            text = state.value.emoji,
            style = MaterialTheme.typography.h1
        )
        Text(
            "WEEK SCREEN \n" +
                "HASHCODE: $screenHashCode\n" +
                "CONTAINER SCREEN KEY : ${screen.screenKey.value}\n" +
                "SCREEN KEY : ${screenKey.value}"
        )
        Button(onClick = {
            coroutineScope.launch(Dispatchers.Main) {
                viewModel.onOpenDayScreenButtonClicked()
            }
        }) {
            Text("Open Day Screen")
        }
        Button(onClick = {
            coroutineScope.launch(Dispatchers.Main) {
                viewModel.onOpenSettingScreenButtonClicked()
            }
        }) {
            Text("Open Settings Screen")
        }
    }
}