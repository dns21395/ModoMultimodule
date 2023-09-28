package uk.nightlines.feature.simple.impl.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.stack.StackNavModel
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.setstack.BaseContainerScreen
import uk.nightlines.feature.simple.impl.di.DaggerSimpleComponent

private const val KEY_COMPONENT = "KEY_SETTINGS_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_SETTINGS_VIEWMODEL"

@Parcelize
class SimpleStack(
    private val stackNavModel: StackNavModel,
) : BaseContainerScreen(stackNavModel) {

    constructor() : this(StackNavModel(emptyList()))

    @Composable
    override fun Content() {
        val coreProvider = LocalCoreProvider.current

        val componentHolder = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_COMPONENT") {
            ComponentHolder(DaggerSimpleComponent.factory().create(coreProvider))
        }

        val viewModel = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_VIEWMODEL") {
            componentHolder.component.viewModel()
        }

        val state = viewModel.state.collectAsStateWithLifecycle()

        SimpleContent(
            state = state.value,
            screenKey = screenKey.toString(),
            onForwardSimpleButtonClicked = { viewModel.onForwardSimpleButtonClicked() },
            onReplaceSimpleButtonClicked = { viewModel.onReplaceSimpleButtonClicked() },
            onForwardComplexButtonClicked = { viewModel.onForwardComplexButtonClicked() },
            onReplaceComplexButtonClicked = { viewModel.onReplaceComplexButtonClicked() })
    }
}
