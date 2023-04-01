package uk.nightlines.core.navigation.setstack

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Dialog
import com.github.terrakok.modo.*
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState

abstract class BaseContainerScreen(
    navigationModel: StackNavModel
) : StackScreen(navigationModel) {

    @Composable
    override fun Content() {
        BaseTopScreenContent()
    }

    @OptIn(ExperimentalModoApi::class)
    @Composable
    private fun BaseTopScreenContent(content: RendererContent<StackState> = defaultRendererContent) {
        val screensToRender: Pair<Screen?, DialogScreen?> by remember {
            derivedStateOf {
                val stack = navigationState.stack
                val topScreen = stack.lastOrNull()
                if (topScreen is DialogScreen) {
                    val screen = stack.findLast { it !is DialogScreen }!!
                    screen to topScreen
                } else {
                    topScreen to null
                }
            }
        }
        val (screen, dialog) = screensToRender
        if (screen != null) {
            BaseContent(screen, content)
        }
        if (dialog != null) {
            Dialog(
                onDismissRequest = { onBackButtonPressed() },
                properties = remember { dialog.provideDialogProperties() }
            ) {
                BaseContent(screen = dialog)
            }
        }
    }

    @Composable
    private fun BaseContent(
        screen: Screen,
        content: RendererContent<StackState> = defaultRendererContent
    ) {
        val isBackHandlerEnabled by remember {
            derivedStateOf {
                defaultBackHandler && navigationState.getChildScreens().size > 1
            }
        }
        BackHandler(enabled = isBackHandlerEnabled) {
            onBackButtonPressed()
        }
        super.InternalContent(screen, content)
    }

    abstract fun onBackButtonPressed()
}