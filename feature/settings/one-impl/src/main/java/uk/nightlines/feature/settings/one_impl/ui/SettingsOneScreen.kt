package uk.nightlines.feature.settings.one_impl.ui

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
import uk.nightlines.feature.settings.main_api.OpenSettingsTwoScreenCommand
import uk.nightlines.feature.settings.one_impl.di.DaggerSettingsOneComponent

@Parcelize
internal class SettingsOneScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        SettingsOneContent()
    }
}

@Composable
internal fun SettingsOneContent() {
    val coreProvider = LocalCoreProvider.current
    val settingsDependencies = LocalSettingsDependencies.current

    val coroutineScope = rememberCoroutineScope()
    val component = DaggerSettingsOneComponent.factory().create(coreProvider, settingsDependencies)

    Column {
        Text("Settings One")
        Button(
            onClick = {
                val navigation = component.getNavigation()
//
                coroutineScope.launch { navigation.navigate(OpenSettingsTwoScreenCommand) }
            }
        ) {
            Text("Open Settings Two Screen")
        }
    }
}