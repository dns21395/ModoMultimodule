package uk.nightlines.feature.settings.main_impl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsContent(
    state: SettingsStateViewState,
    screenCounter: String,
    screenHashCode: String,
    screenKey: String,
    topScreenContent: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(state.backgroundColor)
    ) {
        Text(
            text = state.emoji,
            style = MaterialTheme.typography.h1
        )
        Text(
            text = "[SET STACK] SCREEN #$screenCounter\n" +
                    "CONTAINER HASHCODE : $screenHashCode\n" +
                    "SCREEN KEY : $screenKey",
            style = MaterialTheme.typography.h6
        )
        topScreenContent()
    }
}