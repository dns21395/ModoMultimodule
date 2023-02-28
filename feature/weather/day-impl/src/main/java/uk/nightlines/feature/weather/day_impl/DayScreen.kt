package uk.nightlines.feature.weather.day_impl

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
import uk.nightlines.feature.weather.day_impl.di.DaggerDayComponent
import uk.nightlines.feature.weather.main_api.LocalDependenciesProvider
import uk.nightlines.feature.weather.main_api.OpenWeekScreenCommand

@Parcelize
class DayScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        DayContent()
    }
}

@Composable
fun DayContent() {
    val coreProvider = LocalCoreProvider.current
    val weatherDependencies = LocalDependenciesProvider.current
    val component = DaggerDayComponent.factory().create(coreProvider, weatherDependencies)

    val coroutineScope = rememberCoroutineScope()

    Column {
        Text("DayScreen")
        Button(onClick = {
            coroutineScope.launch(Dispatchers.Main) {
                component.getWeatherNavigation().navigate(OpenWeekScreenCommand)
            }
        }) {
            Text("Open Week Screen")
        }
    }
}
