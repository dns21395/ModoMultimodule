package uk.nightlines.feature.settings.one_impl

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize

@Parcelize
internal class SettingsOneScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        SettingsOneContent()
    }
}

@Composable
internal fun SettingsOneContent() {
    Column {
        Text("Settings One")
    }
}