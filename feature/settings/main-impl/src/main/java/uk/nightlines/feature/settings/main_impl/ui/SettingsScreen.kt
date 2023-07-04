package uk.nightlines.feature.settings.main_impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.stack.SetStack
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.common.ui.ContainerScreenContent
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.SlideTransition
import uk.nightlines.core.navigation.setstack.BaseContainerScreen
import uk.nightlines.feature.settings.common.LocalDependenciesProvider
import uk.nightlines.feature.settings.main_impl.di.DaggerSettingsComponent

private const val KEY_COMPONENT = "KEY_SETTINGS_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_SETTINGS_VIEWMODEL"

@Parcelize
class SettingsStack(
    private val stackNavModel: StackNavModel,
) : BaseContainerScreen(stackNavModel) {

    constructor() : this(StackNavModel(emptyList()))

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
            viewModel.screensStack.collectLatest { screensStack ->
                dispatch(SetStack(StackState(screensStack)))
            }
        }

        val coroutineScope = rememberCoroutineScope()

        val state = viewModel.state.collectAsStateWithLifecycle()

        CompositionLocalProvider(
            LocalDependenciesProvider provides componentHolder.component
        ) {
            ContainerScreenContent(
                title = "SETTINGS",
                state = state.value,
                screenKey = screenKey.value,
                screenHashCode = hashCode().toString(),
                navigationStack = navigationState.stack,
                onShowOptionsButtonClicked = { viewModel.onShowOptionsButtonClicked() },
                onForwardButtonClicked = { viewModel.onForwardButtonClicked() },
                onReplaceButtonClicked = { viewModel.onReplaceButtonClicked() },
                onRemoveByPositionsEditTextChanged = { viewModel.onRemoveEditTextPositionChanged(it) },
                onRemoveByPositionsButtonClicked = { viewModel.onRemoveScreensButtonClicked() },
                onBackToSecondScreenButtonClicked = { viewModel.onBackToSecondScreenClicked() },
                onBackToRootClicked = { viewModel.onBackToRootClicked() },
                onNewStackButtonClicked = { viewModel.onNewStackButtonClicked() },
                onMultiForwardButtonClicked = { viewModel.onMultiForwardButtonClicked() },
                onNewRootButtonClicked = { viewModel.onNewRootButtonClicked() },
                onContainerButtonClicked = { viewModel.onContainerButtonClicked() }
            ) {
                BaseTopScreenContent(
                    backButtonHandle = {
                        coroutineScope.launch { viewModel.onBackButtonClicked() }
                    }
                ) {
                    SlideTransition()
                }
            }
        }
    }
}