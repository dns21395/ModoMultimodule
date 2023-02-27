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
    val weatherDependencies = LocalDependenciesProvider.current
    val coroutineScope = rememberCoroutineScope()

    Column {
        Text("DayScreen")
        Button(onClick = {
            coroutineScope.launch(Dispatchers.Main) {
                weatherDependencies.getWeatherNavigation().navigate(OpenWeekScreenCommand)
            }
        }) {
            Text("Open Week Screen")
        }
    }
}
