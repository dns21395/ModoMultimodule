package uk.nightlines.modomultimodule.stack1

import android.util.Log
import androidx.compose.runtime.*
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.parcelize.Parcelize
import uk.nightlines.modomultimodule.di.DaggerAppComponent
import uk.nightlines.modomultimodule.di.LocalNavigationProvider
import uk.nightlines.modomultimodule.navigation.NavigationCommand
import uk.nightlines.modomultimodule.navigation.NavigationForwardCommand
import uk.nightlines.modomultimodule.navigation.OpenWeatherScreenCommand

@Parcelize
class SampleStack(
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor(rootScreen: Screen) : this(StackNavModel(rootScreen))

    @Composable
    override fun Content() {

        val navigationProvider = DaggerAppComponent.builder().build()

        var commands by remember {
            mutableStateOf<NavigationCommand>(NavigationForwardCommand)
        }

//        val commands = navigationProvider.getNavigation().commandsFlow.collectAsState(initial = NavigationForwardCommand)

        LaunchedEffect(key1 = "yes1") {
            navigationProvider.getNavigation().commandsFlow.collect {
                Log.d("GTA5", "launched effect : $it")
                commands = it
            }
        }

        LaunchedEffect(key1 = commands) {
            when (commands) {
                is OpenWeatherScreenCommand -> {
//                    replace(uk.nightlines.feature.weather.ui.SampleStack2(uk.nightlines.feature.weather.ui.SampleScreen3()))
                }
            }
        }

        Log.d("GTA5", "screen changed : ${commands}")

        CompositionLocalProvider(
            LocalNavigationProvider provides navigationProvider
        ) {
            TopScreenContent()
        }
    }
}

