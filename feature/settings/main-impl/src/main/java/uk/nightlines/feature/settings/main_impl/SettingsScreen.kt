package uk.nightlines.feature.settings.main_impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.navigate
import uk.nightlines.feature.settings.common.LocalDependenciesProvider
import uk.nightlines.feature.settings.main_impl.di.DaggerSettingsComponent

private const val KEY_COMPONENT = "KEY_SETTINGS_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_SETTINGS_VIEWMODEL"

@Parcelize
class SettingsStack(
    private val count: Int,
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor(count: Int) : this(count, StackNavModel(emptyList()))

    @Composable
    override fun Content() {
        val coreProvider = LocalCoreProvider.current

        val componentHolder = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_COMPONENT") {
            ComponentHolder(DaggerSettingsComponent.factory().create(coreProvider))
        }

        val viewModel = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_VIEWMODEL") {
            componentHolder.component.viewModel()
        }

        LaunchedEffect(Unit) {
            viewModel.navigationCommands.collectLatest { command ->
                navigate(command)
            }
        }

        val state = viewModel.state.collectAsStateWithLifecycle()

        CompositionLocalProvider(
            LocalDependenciesProvider provides componentHolder.component
        ) {
            Column(modifier = Modifier.fillMaxSize().background(state.value.backgroundColor)) {
                Text(
                    text = state.value.emoji,
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = "SETTINGS SCREEN #$count\n" +
                            "CONTAINER HASCODE : ${this@SettingsStack.hashCode()}\n" +
                            "SCREEN KEY : ${screenKey.value}",
                    style = MaterialTheme.typography.h6

                )
                TopScreenContent()
            }
        }
    }
}