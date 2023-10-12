package uk.nightlines.feature.complex.container_impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.navigate
import uk.nightlines.feature.complex.common.LocalDependenciesProvider
import uk.nightlines.feature.complex.container_impl.di.DaggerContainerComponent

private const val KEY_COMPONENT = "KEY_COMPLEX_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_COMPLEX_VIEWMODEL"

@Parcelize
internal class ComplexStack(
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor() : this(StackNavModel(emptyList()))


    @Composable
    override fun Content() {
        val coreProvider = LocalCoreProvider.current

        val componentHolder = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_COMPONENT") {
            ComponentHolder(DaggerContainerComponent.factory().create(coreProvider))
        }

        val viewModel: ComplexViewModel =
            daggerViewModel(key = "${stackNavModel.screenKey}$KEY_VIEWMODEL") {
                componentHolder.component.viewModel()
            }

        LaunchedEffect(Unit) {
            viewModel.navigationCommands.collectLatest {
                navigate(it)
            }
        }

        val state = viewModel.state.collectAsStateWithLifecycle()

        CompositionLocalProvider(
            LocalDependenciesProvider provides componentHolder.component
        ) {
            ContainerScreenContent(
                title = "CONTAINER",
                state = state.value,
                screenKey = screenKey.value,
                navigationStack = navigationState.stack,
                onForwardWeatherButtonClicked = { viewModel.onForwardWeatherButtonClicked() },
                onReplaceWeatherButtonClicked = { viewModel.onReplaceWeatherButtonClicked() },
                onForwardSettingsButtonClicked = { viewModel.onForwardSettingsButtonClicked() },
                onReplaceSettingsButtonClicked = { viewModel.onReplaceSettingsButtonClicked() },
                topScreenContent = { TopScreenContent() }
            )
        }
    }
}
