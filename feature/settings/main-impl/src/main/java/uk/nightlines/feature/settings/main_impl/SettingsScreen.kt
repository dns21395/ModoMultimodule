package uk.nightlines.feature.settings.main_impl

import androidx.compose.runtime.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.replace
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.navigation.NavigationCommand
import uk.nightlines.feature.settings.main_api.LocalSettingsDependencies
import uk.nightlines.feature.settings.main_api.SettingsDependencies
import uk.nightlines.feature.settings.main_impl.di.DaggerSettingsComponent

@Parcelize
class SettingsStack(
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor() : this(StackNavModel(emptyList()))

    @Composable
    override fun Content() {
        val settingsDependencies = DaggerSettingsComponent.builder().build() as SettingsDependencies

        var currentCommand by remember {
            mutableStateOf<NavigationCommand>(OpenSettingsOneScreenCommand)
        }


        LaunchedEffect(key1 = currentCommand) {
            when (currentCommand) {
                OpenSettingsOneScreenCommand -> replace(settingsDependencies.getOneApi().getScreen())
                OpenSettingsTwoScreenCommand -> replace(settingsDependencies.getTwoApi().getSettingsTwoScreen())
            }
        }


        LaunchedEffect(key1 = "navigation_listener") {
            settingsDependencies.getNavigation().commandsFlow.collect { command ->
                currentCommand = command
            }
        }

        CompositionLocalProvider(
            LocalSettingsDependencies provides settingsDependencies
        ) {
            TopScreenContent()
        }
    }
}