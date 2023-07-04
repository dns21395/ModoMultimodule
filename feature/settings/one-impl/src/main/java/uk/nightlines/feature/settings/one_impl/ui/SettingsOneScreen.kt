package uk.nightlines.feature.settings.one_impl.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.common.ui.SimpleEditTextScreen
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.feature.settings.common.LocalDependenciesProvider
import uk.nightlines.feature.settings.one_impl.di.DaggerSettingsOneComponent

private const val KEY_COMPONENT = "KEY_SETTINGS_ONE_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_SETTINGS_ONE_VIEWMODEL"

@Parcelize
internal class SettingsOneScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        SettingsOneContent(
            hashCode(),
            screenKey
        )
    }
}

@Composable
internal fun SettingsOneContent(
    screenHashCode: Int,
    screenKey: ScreenKey,
) {

    val coreProvider = LocalCoreProvider.current
    val settingsDependencies = LocalDependenciesProvider.current
    val screen = LocalContainerScreen.current

    val componentHolder =
        daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT$screenHashCode") {
            ComponentHolder(
                DaggerSettingsOneComponent.factory().create(coreProvider, settingsDependencies)
            )
        }

    val viewModel = daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL$screenHashCode") {
        componentHolder.component.viewModel()
    }

    val state = viewModel.state.collectAsStateWithLifecycle()

    SimpleEditTextScreen(
        screenName = "Settings One",
        state = state.value,
        screenHashCode = screenHashCode,
        containerScreenKey = screen.screenKey.value,
        screenKey = screenKey.value,
        onForwardButtonClicked = { viewModel.onForwardButtonClicked() },
        onReplaceButtonClicked = { viewModel.onReplaceButtonClicked() },
        onOpenDialogButtonClicked = { viewModel.onOpenWeatherScreenClicked() },
        onEditTextChanged = { viewModel.onTextChangedAction(it) }
    )
}
