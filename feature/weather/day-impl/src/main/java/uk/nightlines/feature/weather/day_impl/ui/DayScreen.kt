package uk.nightlines.feature.weather.day_impl.ui

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
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.day_impl.di.DaggerDayComponent

private const val KEY_COMPONENT = "KEY_WEATHER_DAY_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_WEATHER_DAY_VIEWMODEL"

@Parcelize
class DayScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        DayContent(
            this.hashCode(),
            screenKey
        )
    }
}

@Composable
fun DayContent(
    screenHashCode: Int,
    screenKey: ScreenKey,
) {
    val coreProvider = LocalCoreProvider.current
    val screen = LocalContainerScreen.current
    val weatherDependencies = LocalDependenciesProvider.current

    val component = daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT$screenHashCode") {
        ComponentHolder(DaggerDayComponent.factory().create(coreProvider, weatherDependencies))
    }

    val viewModel =
        daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL$screenHashCode") { component.component.viewModel() }

    val state = viewModel.state.collectAsStateWithLifecycle()

    SimpleEditTextScreen(
        screenName = "Day",
        state = state.value,
        screenHashCode = screenHashCode,
        containerScreenKey = screen.screenKey.value,
        screenKey = screenKey.value,
        onForwardButtonClicked = { viewModel.onForwardButtonClicked() },
        onReplaceButtonClicked = { viewModel.onReplaceButtonClicked() },
        onOpenDialogButtonClicked = { viewModel.onOpenDialogButtonClicked() },
        onEditTextChanged = { viewModel.onTextChangedAction(it) }
    )
}
