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
import uk.nightlines.feature.weather.main_api.LocalWeatherNavigationProvider
import uk.nightlines.feature.weather.main_api.OpenDayScreenCommand
import uk.nightlines.feature.weather.main_api.OpenWeekScreenCommand

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
    val navigation = LocalWeatherNavigationProvider.current
    val coroutineScope = rememberCoroutineScope()

    Column {
        Text("WeekScreen")
        Button(onClick = {
            coroutineScope.launch(Dispatchers.Main) { navigation.navigate(OpenDayScreenCommand) }
        }) {
            Text("Open Day Screen")
        }
    }
}