package uk.nightlines.feature.weather.day_impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.feature.weather.day_impl.di.DaggerDayComponent
import uk.nightlines.feature.weather.common.LocalDependenciesProvider

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
    val component = remember {
        DaggerDayComponent.factory().create(coreProvider, weatherDependencies)
    }
    val viewModel = daggerViewModel { component.viewModel() }

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
