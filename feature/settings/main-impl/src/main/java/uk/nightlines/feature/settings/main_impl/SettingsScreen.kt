package uk.nightlines.feature.settings.main_impl

import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.NavigationReplace
import uk.nightlines.core.navigation.navigate
import uk.nightlines.feature.settings.main_api.LocalSettingsDependencies
import uk.nightlines.feature.settings.main_impl.di.DaggerSettingsComponent

@Parcelize
class SettingsStack(
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor() : this(StackNavModel(emptyList()))

    @Composable
    override fun Content() {
        val coreProvider = LocalCoreProvider.current

        val componentHolder = daggerViewModel {
            ComponentHolder(DaggerSettingsComponent.factory().create(coreProvider))
        }

        val viewModel = daggerViewModel {
            componentHolder.component.viewModel()
        }

        val commands = viewModel.navigationCommands.collectAsState(
            NavigationReplace(componentHolder.component.getOneApi().getScreen())
        )

        LaunchedEffect(key1 = commands.value) {
            navigate(commands.value)
        }

        CompositionLocalProvider(
            LocalSettingsDependencies provides componentHolder.component
        ) {
            TopScreenContent()
        }
    }
}