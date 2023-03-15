package uk.nightlines.feature.weather.week_impl.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
        WeekContent(this@WeekScreen.hashCode())
    }
}

@Composable
fun WeekContent(screenHashCode: Int) {
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

    Column {
        Text("WeekScreen\n" +
                "HASHCODE: $screenHashCode")
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