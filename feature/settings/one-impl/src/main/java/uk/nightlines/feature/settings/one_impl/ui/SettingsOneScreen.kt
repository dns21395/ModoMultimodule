package uk.nightlines.feature.settings.one_impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import uk.nightlines.feature.settings.one_impl.di.DaggerSettingsOneComponent

private const val KEY_COMPONENT = "KEY_SETTINGS_ONE_COMPONENT"
private const val KEY_VIEWMODEL = "KEY_SETTINGS_ONE_VIEWMODEL"

@Parcelize
internal class SettingsOneScreen(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        SettingsOneContent(
            hashCode(),
            screenKey
        )
    }
}

@Composable
internal fun SettingsOneContent(
    screenHashCode: Int,
    screenKey: ScreenKey,
) {

    val coroutineScope = rememberCoroutineScope()

    val coreProvider = LocalCoreProvider.current
    val settingsDependencies = LocalDependenciesProvider.current
    val screen = LocalContainerScreen.current

    val componentHolder =
        daggerViewModel(key = "${screen.screenKey}$KEY_COMPONENT$screenHashCode") {
            ComponentHolder(
                DaggerSettingsOneComponent.factory().create(coreProvider, settingsDependencies)
            )
        }

    val viewModel = daggerViewModel(key = "${screen.screenKey}$KEY_VIEWMODEL$screenHashCode") {
        componentHolder.component.viewModel()
    }

    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(state.value.color)) {
        Text(
            text = state.value.emoji,
            style = MaterialTheme.typography.h1
        )
        Text(
            "SETTINGS ONE\n" +
                    "HASHCODE : $screenHashCode\n" +
                    "CONTAINER SCREEN KEY : ${screen.screenKey.value}\n" +
                    "SCREEN KEY : ${screenKey.value}",
            style = MaterialTheme.typography.h6
        )
        Button(
            onClick = {
                coroutineScope.launch { viewModel.onOpenSettingsTwoScreenClicked() }
            }
        ) {
            Text("Open Settings Two Screen")
        }
        Button(
            onClick = {
                coroutineScope.launch { viewModel.onOpenWeatherScreenClicked() }
            }
        ) {
            Text("Open Weather")
        }
        BasicTextField(
            value = state.value.editText,
            onValueChange = { text -> coroutineScope.launch { viewModel.onTextChangedAction(text) } },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .padding(horizontal = 64.dp) // margin left and right
                        .fillMaxWidth()
                        .background(color = Color(0xFFD2F3F2), shape = RoundedCornerShape(size = 16.dp))
                        .border(
                            width = 2.dp,
                            color = Color(0xFFAAE9E6),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(all = 16.dp), // inner padding
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Favorite icon",
                        tint = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    innerTextField()
                }
            }
        )
    }
}
