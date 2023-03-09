package uk.nightlines.feature.weather.week_impl.ui

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
    val screen = LocalContainerScreen.current
    val weatherDependencies = LocalDependenciesProvider.current

    Log.d("GTA5", "WeekContent : ${weatherDependencies.hashCode()}")

    Log.d("GTA6", "WEEK SCREEN KEY : ${screen.screenKey}")

    val coroutineScope = rememberCoroutineScope()

    val component = remember {
        Log.d("GTA5", "week component creation ${weatherDependencies.hashCode()}")
        DaggerWeekComponent.factory().create(coreProvider, weatherDependencies)
    }

    val viewModel: WeekViewModel = daggerViewModel(key = screen.screenKey.toString()) {
        Log.d("GTA5", "daggeviewmodel week ${weatherDependencies.hashCode()}")

        component.viewModel()
    }

    Column {
        Text("WeekScreen")
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