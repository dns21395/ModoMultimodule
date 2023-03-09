package uk.nightlines.modomultimodule.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.navigate
import uk.nightlines.modomultimodule.di.DaggerAppComponent

@Parcelize
class AppStackScreen(
    private val navigationModel: StackNavModel,
) : StackScreen(navigationModel = navigationModel) {

    constructor() : this(StackNavModel(emptyList()))

    @Composable
    override fun Content() {
        val component = remember {
            DaggerAppComponent.builder().build()
        }
        val viewModel = daggerViewModel { component.viewModel() }
        val commands = viewModel.navigationCommands.collectAsState(NavigationReplace(component.rootScreens().weather(0)))

        val coroutineScope = rememberCoroutineScope()

        BackHandler {
            coroutineScope.launch {
                Log.d("GTA5", "BACK BUTTON")
                viewModel.onBackButtonPressed()
            }
        }

        LaunchedEffect(key1 = commands.value) {
            navigate(commands.value)
        }

        CompositionLocalProvider(
            LocalCoreProvider provides component
        ) {
            TopScreenContent()
        }
    }
}