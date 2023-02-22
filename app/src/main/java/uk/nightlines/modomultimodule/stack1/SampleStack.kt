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
import androidx.compose.runtime.getValue
import com.github.terrakok.modo.stack.replace
import uk.nightlines.modomultimodule.di.NavigationProvider
import uk.nightlines.modomultimodule.navigation.Navigation
import uk.nightlines.modomultimodule.navigation.OpenWeatherScreenCommand
import uk.nightlines.modomultimodule.stack2.SampleScreen3
import uk.nightlines.modomultimodule.stack2.SampleStack2

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
                    replace(SampleStack2(SampleScreen3()))
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

