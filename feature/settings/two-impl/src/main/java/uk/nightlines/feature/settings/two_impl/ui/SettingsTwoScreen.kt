package uk.nightlines.feature.settings.two_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.common.daggerViewModel
import uk.nightlines.core.di.ComponentHolder
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.feature.settings.common.LocalDependenciesProvider
import uk.nightlines.feature.settings.two_impl.di.DaggerSettingsTwoComponent

private const val KEY_COMPONENT = "KEY_SETTINGS_TWO_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_SETTINGS_TWO_VIEWMODEL"

@Parcelize
class SettingsTwoScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        SettingsTwoContent(
            hashCode(),
            screenKey
        )
    }
}

@Composable
fun SettingsTwoContent(
    screenHashCode: Int,
    screenKey: ScreenKey
) {
    val coreProvider = LocalCoreProvider.current
    val settingsDependencies = LocalDependenciesProvider.current
    val screen = LocalContainerScreen.current

    val componentHolder = daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT$screenHashCode") {
        ComponentHolder(
            DaggerSettingsTwoComponent.factory().create(coreProvider, settingsDependencies)
        )
    }

    val viewModel = daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL$screenHashCode") {
        componentHolder.component.viewModel()
    }

    val coroutineScope = rememberCoroutineScope()

    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()
        .background(state.value.backgroundColor)) {
        Text("SCREEN TWO\n" +
                "HASHCODE : $screenHashCode\n" +
                "CONTAINER SCREEN KEY : ${screen.screenKey.value}\n" +
                "SCREEN KEY : ${screenKey.value}",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = state.value.emoji,
            style = MaterialTheme.typography.h1
        )

        Button(onClick = {
            coroutineScope.launch { viewModel.openSettingsOneScreenButtonClicked() }
        }) {
            Text("Open Settings One Screen")
        }
    }
}