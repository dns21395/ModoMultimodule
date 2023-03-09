package uk.nightlines.feature.weather.day_impl.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.day_impl.di.DaggerDayComponent

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
    val screen = LocalContainerScreen.current
    val weatherDependencies = LocalDependenciesProvider.current
    val component = remember {
        DaggerDayComponent.factory().create(coreProvider, weatherDependencies)
    }

    Log.d("GTA5", "DayContent ; ${weatherDependencies.hashCode()}")
    Log.d("GTA6", "DAY SCREEN KEY : ${screen.screenKey}")

    val viewModel =  daggerViewModel(key = screen.screenKey.toString())  { component.viewModel() }

    val coroutineScope = rememberCoroutineScope()

    Column {
        Text("DayScreen")
        Button(onClick = {
            coroutineScope.launch(Dispatchers.Main) {
                viewModel.onOpenWeekScreenButtonClicked()
            }
        }) {
            Text("Open Week Screen")
        }
    }
}
