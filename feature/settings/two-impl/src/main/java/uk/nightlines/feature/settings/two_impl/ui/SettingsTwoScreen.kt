package uk.nightlines.feature.settings.two_impl.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.common.ui.SimpleScreenContent
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.feature.settings.common.LocalDependenciesProvider
import uk.nightlines.feature.settings.two_impl.di.DaggerSettingsTwoComponent

private const val KEY_COMPONENT = "KEY_SETTINGS_TWO_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_SETTINGS_TWO_VIEWMODEL"

@Parcelize
class SettingsTwoScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        SettingsTwoContent(
            hashCode(),
            screenKey
        )
    }
}

@Composable
fun SettingsTwoContent(
    screenHashCode: Int,
    screenKey: ScreenKey
) {
    val coreProvider = LocalCoreProvider.current
    val settingsDependencies = LocalDependenciesProvider.current
    val containerScreen = LocalContainerScreen.current

    val componentHolder =
        daggerViewModel(key = "${containerScreen.screenKey}$KEY_COMPONENT$screenHashCode") {
            ComponentHolder(
                DaggerSettingsTwoComponent.factory().create(coreProvider, settingsDependencies)
            )
        }

    val viewModel =
        daggerViewModel(key = "${containerScreen.screenKey}$KEY_VIEWMODEL$screenHashCode") {
            componentHolder.component.viewModel()
        }

    val state = viewModel.state.collectAsStateWithLifecycle()

    SimpleScreenContent(
        screenName = "SETTINGS TWO",
        screenHashCode = screenHashCode,
        dependenciesProviderHashCode = settingsDependencies.hashCode(),
        containerKeyScreen = containerScreen.screenKey.value,
        keyScreen = screenKey.value,
        state = state.value,
        onForwardButtonClicked = { viewModel.onForwardButtonClicked() },
        onReplaceButtonClicked = { viewModel.onReplaceButtonClicked() },
        onSettingsButtonClicked = { viewModel.onOpenWeekButtonClicked() }
    )
}