package uk.nightlines.modomultimodule

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.github.terrakok.modo.*
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import uk.nightlines.modomultimodule.di.LocalNavigationProvider
import uk.nightlines.modomultimodule.navigation.NavigationForwardCommand
import uk.nightlines.modomultimodule.navigation.NavigationForwardRootCommand

@Parcelize
class SampleScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        SampleContent()
    }
}

@Parcelize
class SampleScreen2(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        SampleContent2()
    }
}

@Composable
fun SampleContent() {
    val navigation = LocalNavigationProvider.current

    val coroutineScope = rememberCoroutineScope()

    Text(text = "Hello, World!")

    Button(onClick = {
        coroutineScope.launch {
            navigation.getNavigation().commandsFlow.emit(NavigationForwardRootCommand)
        }
    }) {
        Text("open screen 2")
    }
}

@Composable
fun SampleContent2() {
    val navigation = LocalNavigationProvider.current

    val coroutineScope = rememberCoroutineScope()

    Text(text = "Hello, Screen 2")

    Button(onClick = {
        coroutineScope.launch {
            navigation.getNavigation().commandsFlow.emit(NavigationForwardCommand)
        }
    }) {
        Text("open screen 1")
    }
}