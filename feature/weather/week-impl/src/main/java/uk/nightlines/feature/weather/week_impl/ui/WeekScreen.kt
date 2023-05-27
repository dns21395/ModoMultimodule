package uk.nightlines.feature.weather.week_impl.ui

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
import uk.nightlines.feature.weather.common.LocalDependenciesProvider
import uk.nightlines.feature.weather.week_impl.di.DaggerWeekComponent

private const val KEY_COMPONENT = "KEY_WEATHER_WEEK_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_WEATHER_WEEK_VIEWMODEL"

@Parcelize
class WeekScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        WeekContent(
            this@WeekScreen.hashCode(),
            screenKey = screenKey
        )
    }
}

@Composable
fun WeekContent(
    screenHashCode: Int,
    screenKey: ScreenKey,
) {
    val coreProvider = LocalCoreProvider.current
    val screen = LocalContainerScreen.current
    val weatherDependencies = LocalDependenciesProvider.current

    val component = daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT$screenHashCode") {
        ComponentHolder(DaggerWeekComponent.factory().create(coreProvider, weatherDependencies))
    }

    val viewModel: WeekViewModel =
        daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL$screenHashCode") {
            component.component.viewModel()
        }

    val state = viewModel.state.collectAsStateWithLifecycle()

    SimpleScreenContent(
        screenName = "WEEK",
        screenHashCode = screenHashCode,
        dependenciesProviderHashCode = weatherDependencies.hashCode(),
        containerKeyScreen = screen.screenKey.value,
        keyScreen = screenKey.value,
        state = state.value,
        onForwardButtonClicked = { viewModel.onForwardButtonClicked() },
        onReplaceButtonClicked = { viewModel.onReplaceButtonClicked() },
        onSettingsButtonClicked = { viewModel.onOpenSettingScreenButtonClicked() })
}