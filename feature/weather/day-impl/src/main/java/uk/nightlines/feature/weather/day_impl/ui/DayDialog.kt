package uk.nightlines.feature.weather.day_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.*
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalModoApi::class)
@Parcelize
class DayDialog(
    override val screenKey: ScreenKey = generateScreenKey()
) : DialogScreen {

    @Composable
    override fun Content() {
        Box(
            Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Text("This is a dialog")
        }
    }
}
