package uk.nightlines.feature.settings.two_impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.core.di.LocalCoreProvider
import uk.nightlines.feature.settings.main_api.LocalSettingsDependencies
import uk.nightlines.feature.settings.main_api.OpenSettingsOneScreenCommand
import uk.nightlines.feature.settings.two_impl.di.DaggerSettingsTwoComponent

@Parcelize
class SettingsTwoScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        SettingsTwoContent()
    }
}

@Composable
fun SettingsTwoContent() {
    val coreProvider = LocalCoreProvider.current
    val settingsDependencies = LocalSettingsDependencies.current
    val component = DaggerSettingsTwoComponent.factory().create(coreProvider, settingsDependencies)

    val coroutineScope = rememberCoroutineScope()

    Column {
        Text("Settings Two Screen")
        Button(onClick = {
            val navigation = component.getNavigation()
            coroutineScope.launch { navigation.navigate(OpenSettingsOneScreenCommand) }
        }) {
            Text("Open Settings One Screen")
        }
    }
}