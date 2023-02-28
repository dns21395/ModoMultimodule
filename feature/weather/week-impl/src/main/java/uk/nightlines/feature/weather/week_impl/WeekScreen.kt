package uk.nightlines.feature.weather.week_impl

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.OpenSettingsCommand
import uk.nightlines.feature.weather.main_api.LocalDependenciesProvider
import uk.nightlines.feature.weather.main_api.OpenDayScreenCommand
import uk.nightlines.feature.weather.week_impl.di.DaggerWeekComponent

@Parcelize
class WeekScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        WeekContent()
    }
}

@Composable
fun WeekContent() {
    val coreProvider = LocalCoreProvider.current
    val weatherDependencies = LocalDependenciesProvider.current
    val coroutineScope = rememberCoroutineScope()

    val component = DaggerWeekComponent.factory().create(
        coreProvider, weatherDependencies
    )

    Column {
        Text("WeekScreen")
        Button(onClick = {
            coroutineScope.launch(Dispatchers.Main) {
                component.getWeatherNavigation().navigate(OpenDayScreenCommand)
            }
        }) {
            Text("Open Day Screen")
        }
        Button(onClick = {
            coroutineScope.launch(Dispatchers.Main) {
                component.getCoreNavigation().navigate(OpenSettingsCommand)
            }
        }) {
            Text("Open Settings Screen")
        }
    }
}