package uk.nightlines.feature.weather.container_impl.ui

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
import uk.nightlines.core.navigation.command.navigate
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.container_impl.di.DaggerContainerComponent

private const val KEY_COMPONENT = "KEY_WEATHER_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_WEATHER_VIEWMODEL"

@Parcelize
internal class WeatherStack(
    private val stackNavModel: StackNavModel,
) : StackScreen(stackNavModel) {

    constructor() : this(StackNavModel(emptyList()))


    @Composable
    override fun Content() {
        val coreProvider = LocalCoreProvider.current

        val componentHolder = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_COMPONENT") {
            ComponentHolder(DaggerContainerComponent.factory().create(coreProvider))
        }

        val viewModel: WeatherViewModel =
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
                title = "WEATHER",
                state = state.value,
                screenKey = screenKey.value,
                screenHashCode = hashCode().toString(),
                navigationStack = navigationState.stack,
                onShowOptionsButtonClicked = { viewModel.onShowOptionsButtonClicked() },
                onForwardWeatherButtonClicked = { viewModel.onForwardWeatherButtonClicked() },
                onReplaceWeatherButtonClicked = { viewModel.onReplaceWeatherButtonClicked() },
                onForwardSettingsButtonClicked = { viewModel.onForwardSettingsButtonClicked() },
                onReplaceSettingsButtonClicked = { viewModel.onReplaceSettingsButtonClicked() },
                onRemoveByPositionsButtonClicked = { viewModel.onRemoveFirstAndThirdScreensButtonClicked() },
                onBackToSecondScreenButtonClicked = { viewModel.onBackToSecondScreenClicked(it) },
                onBackToRootClicked = { viewModel.onBackToRootButtonClicked() },
                onNewStackButtonClicked = { viewModel.openNewStackButtonClicked() },
                onMultiForwardButtonClicked = { viewModel.onMultiForwardButtonClicked() },
                onNewRootButtonClicked = { viewModel.onNewRootButtonClicked() },
                onContainerButtonClicked = { viewModel.onContainerButtonClicked() },
                topScreenContent = { TopScreenContent() }
            )
        }
    }
}