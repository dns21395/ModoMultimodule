package uk.nightlines.modomultimodule

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.terrakok.modo.*
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import kotlinx.parcelize.Parcelize

@Parcelize
class SampleScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        SampleContent(parent as StackScreen)
    }
}

@Composable
fun SampleContent(
    navigator: NavigationContainer<StackState>
) {
    Text(text = "Hello, World!")
}