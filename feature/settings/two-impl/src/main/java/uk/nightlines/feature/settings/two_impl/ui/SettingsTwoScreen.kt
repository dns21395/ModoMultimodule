package uk.nightlines.feature.settings.two_impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
        SettingsTwoContent()
    }
}

@Composable
fun SettingsTwoContent() {
    val coreProvider = LocalCoreProvider.current
    val settingsDependencies = LocalDependenciesProvider.current
    val screen = LocalContainerScreen.current

    val componentHolder = daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT") {
        ComponentHolder(
            DaggerSettingsTwoComponent.factory().create(coreProvider, settingsDependencies)
        )
    }

    val viewModel = daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL") {
        componentHolder.component.viewModel()
    }

    val coroutineScope = rememberCoroutineScope()

    Column {
        Text("Settings Two Screen")
        Button(onClick = {
            coroutineScope.launch { viewModel.openSettingsOneScreenButtonClicked() }
        }) {
            Text("Open Settings One Screen")
        }
    }
}