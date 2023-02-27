package uk.nightlines.modomultimodule.ui

import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.replace
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.core.navigation.OpenSettingsCommand
import uk.nightlines.core.navigation.OpenWeatherCommand
import uk.nightlines.modomultimodule.di.DaggerAppComponent

@Parcelize
class AppStackScreen(
    private val navigationModel: StackNavModel,
) : StackScreen(navigationModel = navigationModel) {

    constructor() : this(StackNavModel(emptyList()))

    @Composable
    override fun Content() {
        val coreProvider = DaggerAppComponent.builder().build()

        val rootScreens = coreProvider.rootScreens()

        var currentCommand by remember {
            mutableStateOf<NavigationCommand>(OpenSettingsCommand)
        }

        LaunchedEffect(key1 = currentCommand) {
            when (currentCommand) {
                OpenWeatherCommand -> replace(rootScreens.weather())
                OpenSettingsCommand -> replace(rootScreens.settings())
            }
        }


        LaunchedEffect(key1 = "navigation_listener") {
            coreProvider.getCoreNavigation().commandsFlow.collect { command ->
                currentCommand = command
            }
        }

        CompositionLocalProvider(
            LocalCoreProvider provides coreProvider
        ) {
            TopScreenContent()
        }
    }
}