package uk.nightlines.feature.settings.main_impl

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.stack.StackNavModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.core.navigation.setstack.BaseContainerScreen
import uk.nightlines.feature.settings.common.LocalDependenciesProvider
import uk.nightlines.feature.settings.main_impl.di.DaggerSettingsComponent

private const val KEY_COMPONENT = "KEY_SETTINGS_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_SETTINGS_VIEWMODEL"

@Parcelize
class SettingsStack(
    private val count: Int,
    private val stackNavModel: StackNavModel,
) : BaseContainerScreen(stackNavModel) {

    constructor(count: Int) : this(count, StackNavModel(emptyList()))

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
            viewModel.navigationCommands.collectLatest { command ->
                Log.d("GTA5", "[SETTINGS] command : $command")
                dispatch(command)
            }
        }

        val coroutineScope = rememberCoroutineScope()

        val state = viewModel.state.collectAsStateWithLifecycle()

        CompositionLocalProvider(
            LocalDependenciesProvider provides componentHolder.component
        ) {
            SettingsContent(
                state = state.value,
                screenCounter = count.toString(),
                screenHashCode = hashCode().toString(),
                screenKey = screenKey.value,
            ) {
                BaseTopScreenContent(
                    backButtonHandle = {
                        coroutineScope.launch { viewModel.onBackButtonClicked() }
                    }
                )
            }
        }
    }
}