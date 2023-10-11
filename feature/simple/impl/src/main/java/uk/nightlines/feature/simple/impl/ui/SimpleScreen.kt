package uk.nightlines.feature.simple.impl.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.feature.simple.impl.di.DaggerSimpleComponent

private const val KEY_COMPONENT = "KEY_SETTINGS_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_SETTINGS_VIEWMODEL"

@Parcelize
class SimpleStack(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {


    @Composable
    override fun Content() {
        val coreProvider = LocalCoreProvider.current

        val componentHolder = daggerViewModel(key = "${screenKey}$KEY_COMPONENT") {
            ComponentHolder(DaggerSimpleComponent.factory().create(coreProvider))
        }

        val viewModel = daggerViewModel(key = "${screenKey}$KEY_VIEWMODEL") {
            componentHolder.component.viewModel()
        }

        val state = viewModel.state.collectAsStateWithLifecycle()

        SimpleContent(
            state = state.value,
            screenKey = screenKey.value,
            onForwardSimpleButtonClicked = { viewModel.onForwardSimpleButtonClicked() },
            onReplaceSimpleButtonClicked = { viewModel.onReplaceSimpleButtonClicked() },
            onForwardComplexButtonClicked = { viewModel.onForwardComplexButtonClicked() },
            onReplaceComplexButtonClicked = { viewModel.onReplaceComplexButtonClicked() })
    }
}
