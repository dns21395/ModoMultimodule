package uk.nightlines.modomultimodule.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.CoreProvider
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.command.navigate
import uk.nightlines.modomultimodule.di.DaggerAppComponent

private const val KEY_COMPONENT = "KEY_APP_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_APP_VIEWMODEL"

@Parcelize
class AppStackScreen(
    private val navigationModel: StackNavModel,
) : StackScreen(navigationModel = navigationModel) {

    constructor() : this(StackNavModel(emptyList()))

    @Composable
    override fun Content() {

        val componentHolder = daggerViewModel(key = KEY_COMPONENT) {
            ComponentHolder(DaggerAppComponent.builder().build())
        }
        val viewModel =
            daggerViewModel(key = KEY_VIEWMODEL) { componentHolder.component.viewModel() }

        LaunchedEffect(Unit) {
            viewModel.navigationCommands.collectLatest { command ->
                navigate(command)
            }
        }

        CompositionLocalProvider(
            LocalCoreProvider provides componentHolder.component as CoreProvider
        ) {
            LazyColumn {
                item {
                    Text(text = "App Container : ${navigationModel.navigationState.stack.map { it.screenKey.value }}")
                }
                item {
                    TopScreenContent()
                }
            }
        }
    }
}