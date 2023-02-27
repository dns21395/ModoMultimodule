package uk.nightlines.feature.settings.two_impl

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize

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
    Column {
        Text("Settings Two Screen")
    }
}