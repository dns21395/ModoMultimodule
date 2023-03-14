package uk.nightlines.modomultimodule.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.CoreProvider
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

        val componentHolder = daggerViewModel(key = "APP_COMP") {
            ComponentHolder(DaggerAppComponent.builder().build())
        }
        val viewModel = daggerViewModel(key = "APP") { componentHolder.component.viewModel() }
        val commands = viewModel.navigationCommands.collectAsState(NavigationReplace(componentHolder.component.rootScreens().weather(0)))

        LaunchedEffect(key1 = commands.value) {
            Log.d("GTA5", "ROOT NAVIGATE : ${commands.value}")
            navigate(commands.value)
        }

        CompositionLocalProvider(
            LocalCoreProvider provides componentHolder.component as CoreProvider
        ) {
            TopScreenContent()
        }
    }
}