package uk.nightlines.feature.settings.main_impl

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.NavigationReplace
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

        val screen = LocalContainerScreen.current

        val componentHolder = daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT") {
            ComponentHolder(DaggerSettingsComponent.factory().create(coreProvider))
        }

        val viewModel = daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL") {
            componentHolder.component.viewModel()
        }

        val commands = viewModel.navigationCommands.collectAsState(
            NavigationReplace(componentHolder.component.getSettingsScreen().oneScreen())
        )

        LaunchedEffect(key1 = commands.value) {
            navigate(commands.value)
        }

        CompositionLocalProvider(
            LocalDependenciesProvider provides componentHolder.component
        ) {
            Column {
                Text(text = "SETTINGS SCREEN #$count")
                TopScreenContent()
            }
        }
    }
}