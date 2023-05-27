package uk.nightlines.feature.weather.main_impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.ui.ContainerScreenContent
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.command.navigate
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.common.ScreenCounter
import uk.nightlines.feature.weather.main_impl.di.DaggerWeatherMainComponent

private const val KEY_COMPONENT = "KEY_WEATHER_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_WEATHER_VIEWMODEL"

@Parcelize
internal class WeatherStack(
    private val stackNavModel: StackNavModel,
    private val counter: Int,
) : StackScreen(stackNavModel), ScreenCounter {

    constructor(counter: Int) : this(StackNavModel(emptyList()), counter)

    override fun getCounter(): Int = counter

    @Composable
    override fun Content() {
        val coreProvider = LocalCoreProvider.current

        val componentHolder = daggerViewModel(key = "${stackNavModel.screenKey}$KEY_COMPONENT") {
            ComponentHolder(DaggerWeatherMainComponent.factory().create(coreProvider))
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
                counter = counter.toString(),
                screenKey = screenKey.value,
                screenHashCode = hashCode().toString(),
                navigationStack = navigationState.stack,
                onShowOptionsButtonClicked = { viewModel.onShowOptionsButtonClicked() },
                onForwardButtonClicked = { viewModel.onOpenNewWeatherScreenButtonClicked() },
                onReplaceButtonClicked = { viewModel.onReplaceButtonClicked() },
                onRemoveByPositionsEditTextChanged = { viewModel.onRemoveEditTextPositionChanged(it) },
                onRemoveByPositionsButtonClicked = { viewModel.onRemoveScreensButtonClicked() },
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
