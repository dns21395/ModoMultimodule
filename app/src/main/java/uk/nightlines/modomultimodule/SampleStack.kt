package uk.nightlines.modomultimodule

import android.util.Log
import androidx.compose.runtime.*
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import uk.nightlines.modomultimodule.di.DaggerAppComponent
import uk.nightlines.modomultimodule.di.LocalNavigationProvider
import uk.nightlines.modomultimodule.navigation.NavigationCommand
import uk.nightlines.modomultimodule.navigation.NavigationForwardCommand
import uk.nightlines.modomultimodule.navigation.NavigationForwardRootCommand

@Parcelize
class SampleStack(
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor(rootScreen: Screen) : this(StackNavModel(rootScreen))

    @Composable
    override fun Content() {

        val navigationProvider = DaggerAppComponent.builder().build()

        val screen = remember {
            mutableStateOf<NavigationCommand>(NavigationForwardCommand)
        }

        if (screen.value is NavigationForwardRootCommand) {


            this.forward(SampleScreen2())
        }

        Log.d("GTA5", "screen changed : ${screen.value}")

        CompositionLocalProvider(
            LocalNavigationProvider provides navigationProvider
        ) {
            TopScreenContent()
        }



        LaunchedEffect("key") {
            navigationProvider.getNavigation().commandsFlow.collect { command ->
                Log.d("GTA5", "command : $command")

                screen.value = command
            }
        }
    }
}

