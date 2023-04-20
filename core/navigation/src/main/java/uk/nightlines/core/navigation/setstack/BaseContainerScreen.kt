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

    }

    @OptIn(ExperimentalModoApi::class)
    @Composable
    fun BaseTopScreenContent(
        backButtonHandle: () -> Unit,
        content: RendererContent<StackState> = defaultRendererContent,
    ) {
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
            BaseContent(screen, backButtonHandle, content)
        }
        if (dialog != null) {
            Dialog(
                onDismissRequest = { backButtonHandle.invoke() },
                properties = remember { dialog.provideDialogProperties() }
            ) {
                BaseContent(screen = dialog, backButtonHandle)
            }
        }
    }

    @Composable
    private fun BaseContent(
        screen: Screen,
        backButtonHandle: () -> Unit,
        content: RendererContent<StackState> = defaultRendererContent,
    ) {
        val isBackHandlerEnabled by remember {
            derivedStateOf {
                defaultBackHandler && navigationState.getChildScreens().size > 1
            }
        }
        BackHandler(enabled = isBackHandlerEnabled) {
            backButtonHandle()
        }
        super.InternalContent(screen, content)
    }
}