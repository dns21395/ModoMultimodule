package uk.nightlines.feature.settings.main_impl

import androidx.compose.ui.graphics.Color
import uk.nightlines.core.common.emojis
import kotlin.random.Random

data class SettingsStateViewState(
    val backgroundColor: Color = Color(Random.nextInt()),
    val positionEditText: String = "",
    val emoji: String = emojis[Random.nextInt(emojis.size - 1)],
    val isOptionsVisible: Boolean = false
)